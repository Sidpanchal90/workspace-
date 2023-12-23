package com.blogs.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogs.dau.CategoryRepo;
import com.blogs.entities.Category;
import com.blogs.exception.ResourceNotFoundException;
import com.blogs.payloads.CategoryDto;
import com.blogs.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category cat = this.modelmapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(cat);

		return this.modelmapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedcat = this.categoryRepo.save(cat);

		return this.modelmapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {

		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelmapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
		return catDtos;
	}

}
