package Spring5MvcRestApplication.services;

import Spring5MvcRestApplication.v1.mapper.CategoryMapper;
import Spring5MvcRestApplication.v1.model.CategoryDTO;
import org.springframework.stereotype.Service;
import Spring5MvcRestApplication.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCatergoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper
                .categoryToCatergoryDTO(categoryRepository.findByName(name));
    }

}
