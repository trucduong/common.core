package core.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.common.exception.CommonException;
import core.common.utils.CommonConstants;
import core.dao.dto.BaseDto;
import core.dao.entities.IEntity;
import core.dao.utils.BaseDao;
import core.service.utils.CRUDServiceAction;
import core.service.utils.ServiceErrorCode;
import core.service.utils.ServiceResult;

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

	@RequestMapping(value = CRUDServiceAction.READ, method = RequestMethod.GET)
	public ServiceResult read(@PathVariable(value = CRUDServiceAction.PARAM_ID) long id) throws CommonException {
		init(ServiceErrorCode.NOT_FOUND);
		E entity = getDao().find(id);

		if (entity == null) {
			return error(ServiceErrorCode.NOT_FOUND);
		}

		D dto = createDto();
		entity.unBind(dto);

		return success(dto);
	}

	@RequestMapping(value = CRUDServiceAction.READ_ALL, method = RequestMethod.GET)
	public ServiceResult readAll() throws CommonException {
		init(ServiceErrorCode.NOT_FOUND);
		List<E> entities = getDao().getAllData();

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

	@RequestMapping(value = CRUDServiceAction.READ_ALL_BY, method = RequestMethod.GET)
	public ServiceResult readAllBy(@PathVariable(value = CRUDServiceAction.PARAM_NAME) String name,
			@PathVariable(value = CRUDServiceAction.PARAM_VALUES) String values) throws CommonException {
		init(ServiceErrorCode.NOT_FOUND);

		String[] valueList = values.split(CommonConstants.SEPERATOR);
		List<E> entities = getDao().getAllDataByColumns(name, valueList);

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

	@RequestMapping(value = CRUDServiceAction.READ_BY, method = RequestMethod.GET)
	public ServiceResult readBy(@PathVariable(value = CRUDServiceAction.PARAM_NAME) String name,
			@PathVariable(value = CRUDServiceAction.PARAM_VALUE) String value) throws CommonException {
		init(ServiceErrorCode.NOT_FOUND);
		List<E> entities = getDao().getAllDataByColumn(name, value);

		if (entities == null || entities.isEmpty()) {
			return error(ServiceErrorCode.NOT_FOUND);
		}

		D dto = createDto();
		entities.get(0).unBind(dto);

		return success(dto);
	}

	@RequestMapping(value = CRUDServiceAction.CREATE, method = RequestMethod.POST)
	public ServiceResult create(@RequestBody D dto) throws CommonException {
		init(ServiceErrorCode.CREATE_ERROR);
		dto.setId(0);
		E entity = createEntity();
		onBeforeCreate(entity, dto);
		getDao().create(entity);
		onAfterCreate(entity);
		
		entity.unBind(dto);
		return success(dto);
	}

	@RequestMapping(value = CRUDServiceAction.UPDATE, method = RequestMethod.POST)
	public ServiceResult update(@RequestBody D dto, @PathVariable("id") long id, @RequestParam(name="action", required=false, defaultValue="") String action) throws CommonException {
		init(ServiceErrorCode.UPDATE_ERROR);
		E entity = getDao().find(id);
		if (entity == null) {
			return error(ServiceErrorCode.NOT_FOUND);
		}
		onBeforeUpdate(entity, dto, action);
		getDao().update(entity);
		onAfterUpdate(entity);
		return success(dto);
	}

	@RequestMapping(value = CRUDServiceAction.DELETE, method = RequestMethod.POST)
	public ServiceResult delete(@PathVariable("id") long id) throws CommonException {
		init(ServiceErrorCode.DELTE_ERROR);
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
