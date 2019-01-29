package cn.ldbz.item.service;

import cn.ldbz.pojo.LdbzItemComment;
import cn.ldbz.pojo.LdbzResult;

public interface ItemCommentService {

	LdbzResult countItemComment(LdbzItemComment entity);

	LdbzResult getItemCommentPage(LdbzItemComment entity, int pn, int limit);
	
}