package com.iam.service.service;

import com.iam.service.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T, ID, CreateDTO, UpdateDTO> {
    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract T mapToEntity(CreateDTO createDto);
    protected abstract T mapToEntity(UpdateDTO updateDto, T existingDto);
    protected abstract RuntimeException getNotFoundException();

    @Transactional(readOnly = true)
    public List<T> findAll(){
        return getRepository().findAll();
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
