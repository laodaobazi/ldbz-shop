package cn.ldbz.admin.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import cn.ldbz.pojo.LdbzResult;

@Configuration
@Controller
@RequestMapping("/admin")
public class AdminPictureController {
	
	private static Logger logger = Logger.getLogger(AdminPictureController.class);
	
	/**
     * 上传的根路径
     */
    @Value("${upload.root}")
    private String UPLOAD_ROOT;
    /**
     * 上传文件的后缀
     */
    @Value("${upload.allow.suffix}")
    private String UPLOAD_ALLOW_SUFFIX;
    /**
     * 上传文件的后缀
     */
    @Value("${upload.allow.size}")
    private long UPLOAD_ALLOW_SIZE;
    
    /**
     * 监听配置项是否有修改
     */
    @ApolloConfigChangeListener
	public void onChange(ConfigChangeEvent changeEvent) {
		for (String key : changeEvent.changedKeys()) {
			ConfigChange change = changeEvent.getChange(key);
			logger.debug(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
					change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
			switch(key) {
				case "upload.root" : 
					UPLOAD_ROOT = change.getNewValue() ;
					break ;
				case "upload.allow.suffix" : 
					UPLOAD_ALLOW_SUFFIX = change.getNewValue() ;
					break ;
				case "upload.allow.size" : 
					UPLOAD_ALLOW_SIZE = Long.valueOf(change.getNewValue()) ;
			}
		}
	}
    
    
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(-1);
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
    
    
    
    

	@ResponseBody
    @RequestMapping(value="upload",method=RequestMethod.POST) 
    public LdbzResult multifileUpload(@RequestParam("file") MultipartFile file,
    		HttpServletRequest request, RedirectAttributes redirectAttributes){
		
			String module = request.getParameter("module");
			if(StringUtils.isEmpty(module)) {
            	return LdbzResult.build(500, "module_is_blank", "false");
			}
            if(file==null || file.isEmpty()){
            	return LdbzResult.build(500, "file_not_found", "false");
            }else{
            	String filename = file.getOriginalFilename();
            	String _suffix = StringUtils.substringAfterLast(filename, ".") ;
				boolean tempAllowState = false ;
				String[] arr = UPLOAD_ALLOW_SUFFIX.split(",") ;
				for(String str : arr){
					if(str.equalsIgnoreCase(_suffix)){
						tempAllowState = true ;
					}
				}
				if(!tempAllowState){
					return LdbzResult.build(500, "filetype_not_allow", "false");
				}
				
            	long size = file.getSize();
				if(size > UPLOAD_ALLOW_SIZE){
					return LdbzResult.build(500, "filesize_not_allow", "false");
				}
				
				String path = String.format("%s/%s/%s", "/uploadfiles", "ad" , 
						DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
				String filepath = String.format("%s%s" , UPLOAD_ROOT , path) ;
                try {
                	FileUtils.forceMkdir(new File(filepath));
                	filename = String.format("/%s.%s", System.currentTimeMillis() , _suffix) ;
                	File dest = new File(filepath + filename);
                	if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                		dest.getParentFile().mkdir();
                	}
                    file.transferTo(dest);
                    return LdbzResult.ok(path+filename);
                }catch (Exception e) {
                    e.printStackTrace();
                    return LdbzResult.build(500, "upload_error", "false");
                } 
            }
    }
    
    

//	@ResponseBody
//	@RequestMapping(value="upload/{module}", method=RequestMethod.POST)
//	public LdbzResult upload(HttpServletRequest request,@PathVariable("module") String module) {
//
//		DiskFileItemFactory factory = new DiskFileItemFactory();
//		factory.setRepository(new File(String.format("%s%s" , UPLOAD_ROOT , "/temp")));
//		ServletFileUpload upload = new ServletFileUpload(factory);
//		upload.setHeaderEncoding("UTF-8");
//		upload.setSizeMax(UPLOAD_ALLOW_SIZE); //系统默认文件上传上限
//		
//		if (!ServletFileUpload.isMultipartContent(request)) {
//			return LdbzResult.build(500, "file_not_found", "false");
//		}
//		
//		try{
//			List<FileItem> list = upload.parseRequest(request);
//			String allowSuffix="" , allowSize="" ;
//			for (FileItem item : list) {
//				if (item.isFormField()) {
//					if("allowSuffix".equals(item.getFieldName())){
//						allowSuffix = item.getString("utf-8") ;
//					}else if("allowSize".equals(item.getFieldName())){
//						allowSize = item.getString("utf-8") ;
//					}
//				} else {
//					//判断模块
//					if(module==null || "".equals(module)){
//						return LdbzResult.build(500, "module_error", "false");
//					}
//					String filename = item.getName();
//					String _suffix = StringUtils.substringAfterLast(filename, ".") ;
//					//判断后缀(如果自己传递了就用自己的否则用系统默认的)
//					boolean tempAllowState = false ;
//					String[] arr = ("".equals(allowSuffix)?UPLOAD_ALLOW_SUFFIX:allowSuffix).split(",") ;
//					for(String str : arr){
//						if(str.equalsIgnoreCase(_suffix)){
//							tempAllowState = true ;
//						}
//					}
//					if(!tempAllowState){
//						return LdbzResult.build(500, "filetype_not_allow", "false");
//					}
//					
//					//判断大小(如果自己传递了大小则进行校验，否则忽略)
//					if(!"".equals(allowSize) && item.getSize()>Integer.parseInt(allowSize)){
//						return LdbzResult.build(500, "filesize_not_allow", "false");
//					}
//					
//					String path = String.format("%s/%s/%s", "uploadfiles", module , 
//							DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
//					String filepath = String.format("%s%s" , UPLOAD_ROOT , path) ;
//					FileUtils.forceMkdir(new File(filepath));
//					
//					File file = null ;
//					filename = String.format("/%s.%s", System.currentTimeMillis() , _suffix) ;
//					//如果不分片上传
//					file = new File(filepath+filename);
//					FileUtils.copyInputStreamToFile(item.getInputStream(), file);
//					// 删除处理文件上传时生成的临时文件
//					item.delete();
//					//上传成功
//					return LdbzResult.ok(path+filename);
//				}
//			}
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		//上传失败
//		return LdbzResult.build(500, "upload_error", "false");
//	}
	
}
