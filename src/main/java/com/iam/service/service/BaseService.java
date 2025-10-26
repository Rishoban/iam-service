package com.iam.service.service;

import com.iam.service.mapper.PageOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T, ID, CreateDTO, UpdateDTO> {
    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract T mapToEntity(CreateDTO createDto);
    protected abstract T mapToEntity(UpdateDTO updateDto, T existingDto);
    protected abstract RuntimeException getNotFoundException();

    @Transactional(readOnly = true)
    public List<T> findAll(PageOption pageOptions){
        Pageable pageCondition = PageRequest.of(pageOptions.getStartIndex(), pageOptions.getEndIndex(),
                pageOptions.getSortOder().equalsIgnoreCase("desc") ? Sort.by(pageOptions.getSortBy()).descending() : Sort.by(pageOptions.getSortBy()).ascending());
        Page<T> result = getRepository().findAll(pageCondition);
        return result.stream()
                .toList();
    }

    @Transactional(readOnly = true)
    public T findById(ID id){
        return getRepository().findById(id)
                .orElseThrow(this::getNotFoundException);
    }

    @Transactional
    public T createResource(CreateDTO createDTO){
        T entiy = mapToEntity(createDTO);
        return getRepository().save(entiy);
    }

    @Transactional
    public T updateResource(ID id, UpdateDTO updateDto){
        T existingEntity = getRepository().findById(id)
                .orElseThrow(this::getNotFoundException);
        T entity = mapToEntity(updateDto, existingEntity);
        return getRepository().save(entity);
    }

    @Transactional
    public void deleteResource(ID id){
        if(getRepository().existsById(id)){
            throw getNotFoundException();
        }
        getRepository().deleteById(id);
    }

}
