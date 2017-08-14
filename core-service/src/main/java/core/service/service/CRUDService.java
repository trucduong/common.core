package core.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.common.exception.CommonException;
import core.common.query.PagingInfo;
import core.common.query.RequestParamHelper;
import core.common.query.SortInfo;
import core.common.reflect.ObjectModifier;
import core.dao.dao.BaseDao;
import core.dao.entities.IEntity;
import core.service.invoke.ServiceResult;
import core.service.utils.ServiceErrorCode;
import core.shared.dto.BaseDto;
import core.shared.utils.CRUDServiceConst;

public abstract class CRUDService<E extends IEntity, D extends BaseDto> extends BaseService {

	protected abstract BaseDao<E> getDao();

	protected abstract E createEntity();

	protected abstract D createDto();
	
	protected void bindRealtionShip(E entity, D dto) { }
	
	protected void onDeleteSucceed(long id) { }
	
	protected void onBeforeUpdate(E entity, D dto, String action) {
		entity.bind(dto);
		bindRealtionShip(entity, dto);
	}
	
	protected void onAfterUpdate(E entity) {}
	
	protected void onBeforeCreate(E entity, D dto) {
		entity.bind(dto);
		bindRealtionShip(entity, dto);
	}
	
	protected void onAfterCreate(E entity) {}

	@RequestMapping(value = CRUDServiceConst.READ, method = RequestMethod.GET)
	public ServiceResult read(@RequestParam(value = CRUDServiceConst.PARAM_ID, required=true) long id)
			throws CommonException {
		E entity = getDao().find(id);

		if (entity == null) {
			return error(ServiceErrorCode.NOT_FOUND);
		}

		D dto = createDto();
		entity.unBind(dto);

		return success(dto);
	}

	@RequestMapping(value = CRUDServiceConst.READ_ALL, method = RequestMethod.GET)
	public ServiceResult readAll(HttpServletRequest request) throws CommonException {
		SortInfo sortInfo = RequestParamHelper.getSortInfo(request.getParameterMap());
		PagingInfo paringInfo = RequestParamHelper.getPagingInfo(request.getParameterMap());
		List<E> entities = getDao().getAllData(sortInfo, paringInfo);
		
		if (entities == null || entities.isEmpty()) {
			return error(ServiceErrorCode.NOT_FOUND);
		}

		List<D> dtos = new ArrayList<>();
		for (E e : entities) {
			D d = createDto();
			e.unBind(d);
			dtos.add(d);
		}

		return success(dtos);
	}

//	@RequestMapping(value = CRUDServiceActionConst.READ_ALL_BY, method = RequestMethod.GET)
//	public ServiceResult readAllBy(@PathVariable(value = CRUDServiceActionConst.PARAM_NAME) String name,
//			@PathVariable(value = CRUDServiceActionConst.PARAM_VALUES) String values) throws CommonException {
//		String[] valueList = values.split(CommonConstants.SEPERATOR);
//		List<E> entities = getDao().getAllDataByColumns(name, valueList);
//
//		if (entities == null || entities.isEmpty()) {
//			return error(ServiceErrorCode.NOT_FOUND);
//		}
//
//		List<D> dtos = new ArrayList<>();
//		for (E e : entities) {
//			D d = createDto();
//			e.unBind(d);
//			dtos.add(d);
//		}
//
//		return success(dtos);
//	}

	@RequestMapping(value = CRUDServiceConst.READ_BY, method = RequestMethod.GET)
	public ServiceResult readBy(HttpServletRequest request) throws CommonException {
		SortInfo sortInfo = RequestParamHelper.getSortInfo(request.getParameterMap());
		PagingInfo paringInfo = RequestParamHelper.getPagingInfo(request.getParameterMap());
		
		String name = request.getParameter(CRUDServiceConst.PARAM_NAME);
		String value = request.getParameter(CRUDServiceConst.PARAM_VALUE);
		
		List<E> entities = getDao().getAllDataByColumn(name, value, sortInfo, paringInfo);

		if (entities == null || entities.isEmpty()) {
			return error(ServiceErrorCode.NOT_FOUND);
		}

		List<D> dtos = new ArrayList<D>();
		for (E e : entities) {
		    D d = createDto();
		    e.unBind(d);
	        dtos.add(d);
        }

		return success(dtos);
	}

	@Transactional
	@RequestMapping(value = CRUDServiceConst.CREATE, method = RequestMethod.POST)
	public ServiceResult create(@RequestBody D dto) throws CommonException {
		dto.setId(0l);
		E entity = createEntity();
		onBeforeCreate(entity, dto);
		getDao().create(entity);
		onAfterCreate(entity);
		
		entity.unBind(dto);
		return success(dto);
	}

	@Transactional
	@RequestMapping(value = CRUDServiceConst.UPDATE, method = RequestMethod.POST)
	public ServiceResult update(@RequestBody D dto, @PathVariable("id") long id, @RequestParam(name="action", required=false, defaultValue="") String action) throws CommonException {
		E entity = getDao().find(id);
		if (entity == null) {
			return error(ServiceErrorCode.NOT_FOUND);
		}
		onBeforeUpdate(entity, dto, action);
		getDao().update(entity);
		onAfterUpdate(entity);
		return success(dto);
	}

    @Transactional
    @RequestMapping(value = CRUDServiceConst.UPDATE_ATTRIBUTE, method = RequestMethod.POST)
    public ServiceResult update1(@PathVariable("id") long id, @RequestParam Map<String, Object> params)
            throws CommonException {
//        HttpServletRequest request
//        Map<String, String[]> params = request.getParameterMap();
        if (params.isEmpty()) {
            return error(ServiceErrorCode.PARAMETER_ERROR);
        }
        E entity = getDao().find(id);
        if (entity == null) {
            return error(ServiceErrorCode.NOT_FOUND);
        }

        for (String key : params.keySet()) {
            ObjectModifier.set(entity, key, params.get(key));
        }
        getDao().update(entity);
        onAfterUpdate(entity);
        return success(true);
    }

	@Transactional
	@RequestMapping(value = CRUDServiceConst.DELETE, method = RequestMethod.POST)
	public ServiceResult delete(@PathVariable("id") long id) throws CommonException {
		getDao().delete(id);
		onDeleteSucceed(id);
		return success(id);
	}

	protected ServiceResult createOrUpdate(D dto, long id) throws CommonException {
		E entity = getDao().find(id);
		if (entity == null) {
			entity = createEntity();
		}
		entity.bind(dto);
		getDao().update(entity);

		return success(dto);
	}
}
