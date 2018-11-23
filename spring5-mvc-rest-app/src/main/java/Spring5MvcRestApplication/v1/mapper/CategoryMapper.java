package Spring5MvcRestApplication.v1.mapper;

import Spring5MvcRestApplication.v1.model.CategoryDTO;
import Spring5MvcRestApplication.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCatergoryDTO(Category category);
}
