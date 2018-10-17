package cn.ldbz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LdbzBaseMapper<T> {

	/**
	 * 根据实体传递的条件查询符合条件的记录
	 * @param entity
	 * @return
	 */
	List<T> selectByEntity(@Param("entity")T entity , @Param("start")int start , @Param("limit")int limit);
	
	/**
	 * 根据Id获取实体
	 * @param id
	 * @return
	 */
	T selectByKey(Long id);
	
	/**
	 * 根据Id删除记录
	 * @param id
	 * @return
	 */
	int deleteByKey(Long id);
	
	/**
	 * 根据Ids删除记录
	 * @param ids
	 * @return
	 */
	int deleteByKeys(List<Long> ids);
	
	/**
	 * 根据实体传递的条件删除记录
	 * @param entity
	 * @return
	 */
	int deleteByEntity(T entity);
	
	/**
	 * 保存实体记录
	 * @param entity
	 * @return
	 */
	int insertByEntity(T entity);
	
	/**
	 * 保存传递有值的记录
	 * @param entity
	 * @return
	 */
	int insertBySelective(T entity);
	
	/**
	 * 根据实体传进来的条件统计数量
	 * @param entity
	 * @return
	 */
	long countByEntity(T entity);
	
	/**
	 * 根据条件更新有值的字段
	 * @param entity
	 * @return
	 */
	int updateBySelective(T entity);
	
	/**
	 * 根据条件更新整个实体
	 * @param entity
	 * @return
	 */
	int updateByEntity(T entity);
	
	/**
	 * 根据主键更新有值的实体
	 * @param id
	 * @return
	 */
	int updateByKeySelective(T entity);
	
	/**
	 * 根据主键更新整个实体
	 * @param id
	 * @return
	 */
	int updateByKey(T entity);
}
