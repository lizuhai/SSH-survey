package me.zhli.web.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import me.zhli.web.surveypark.dao.BaseDao;
import me.zhli.web.surveypark.model.security.Right;
import me.zhli.web.surveypark.service.RightService;
import me.zhli.web.surveypark.util.DataUtil;
import me.zhli.web.surveypark.util.StringUtil;
import me.zhli.web.surveypark.util.ValidateUtil;

@Service("rightService")
public class RithtServiceImpl<T> extends BaseServiceImpl<Right> implements RightService {
	
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}
	
	/**
	 * 保存/更新权限
	 */
	public void sageOrUpdateRight(Right r) {
		int pos = 0;
		long code = 1L;
		// 插入（保存）
		if(r.getId() == null) {
			String hql = "select max(r.rightPos), max(r.rightCode) from Right r "
					+ "where r.rightPos = (select max(rr.rightPos) from Right rr)";
			Object [] arr = (Object[]) this.uniqueResult(hql);
			Integer topPos = (Integer) arr[0];
			Long topCode = (Long) arr[1];
			// 没有权限
			if(topPos == null) {
				pos = 0;
				code = 1L;
			} else {
				// 权限码是否达到最大值
				if(topCode >= (1L << 60)) {
					pos = topPos + 1;
					code = 1;
				} else {
					pos = topPos;
					code = topCode << 1;
				}
			}
			r.setRightPos(pos);
			r.setRightCode(code);
		}
		// 保存/更新
		this.saveOrUpdateEntity(r);
	}

	@Override
	public void appendRightByUrl(String url) {
		String hql = "select count(*) from Right r where r.rightUrl = ?";
		Long count = (Long) this.uniqueResult(hql, url);
		if(count == 0) {
			Right r = new Right();
			r.setRightUrl(url);
			this.sageOrUpdateRight(r);
		}
	}

	@Override
	public void batchUpdateRights(List<Right> allRights) {
		String hql = "update Right r set r.rightName =?, r.common = ? where r.id = ?";
		if(ValidateUtil.isValidate(allRights)) {
			for(Right r : allRights) {
				this.batchEntityByHQL(hql, r.getRightName(), r.isCommon(), r.getId());
			}
		}
	}

	/**
	 * 查询指定范围内的权限
	 */
	@Override
	public List<Right> findRightsInPage(Integer[] ids) {
		if(ValidateUtil.isValidate(ids)) {
			String hql = "from Right r where r.id in (" + StringUtil.arr2Str(ids) + ")";
			return findEntityByHQL(hql);
		}
		return null;
	}
	
	/**
	 * 查询不再指定范围内的权限
	 */
	@Override
	public List<Right> findRightsNotInRange(Set<Right> rights) {
		if(!ValidateUtil.isValidate(rights)) {
			return this.findAllEntities();
		} else {
//			System.out.println(rights);
			String hql = "from Right r where r.id not in(" + DataUtil.extractEntityIds(rights) + ")";
			return this.findEntityByHQL(hql);
		}
	}

	@Override
	public int getMaxRightPos() {
		String hql = "select max(r.rightPos) from Right r";
		Integer pos = (Integer) this.uniqueResult(hql);
		return pos == null ? 0 : pos;
	}

}
