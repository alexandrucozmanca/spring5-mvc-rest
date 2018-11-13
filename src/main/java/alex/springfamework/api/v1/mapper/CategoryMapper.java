package alex.springfamework.api.v1.mapper;

import alex.springfamework.api.v1.model.CategoryDTO;
import alex.springfamework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCatergoryDTO(Category category);
}
