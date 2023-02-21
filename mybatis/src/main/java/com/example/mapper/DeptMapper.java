package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.DeptListDto;

@Mapper
public interface DeptMapper {

	List<DeptListDto> getDeptList();
}
