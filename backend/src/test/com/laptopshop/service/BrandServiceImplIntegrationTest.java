package com.laptopshop.service;

import com.laptopshop.dto.BrandDto;
import com.laptopshop.entity.Brand;
import com.laptopshop.exception.BadRequestException;
import com.laptopshop.exception.ResourceNotFoundException;
import com.laptopshop.repository.BrandRepository;
import com.laptopshop.service.impl.BrandServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class BrandServiceImplIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public BrandService brandService() {
            return new BrandServiceImpl();
        }
    }

    @Autowired
    private BrandService brandService;

    // every dependent objects must be declare as a MockBean
    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    // Get all
    @Test
    public void testGetAllBrands() {
        BrandDto brand1 = new BrandDto("Apple", "path_to_image");
        BrandDto brand2 = new BrandDto("Samsung", "path_to_image");
        List<BrandDto> brandDtoList = Arrays.asList(brand1, brand2);

        Mockito.when(brandRepository.getAll()).thenReturn(brandDtoList);

        List<BrandDto> allBrands = brandService.getAll();
        assertThat(allBrands.size()).isEqualTo(2);
    }

    // Get by name
    @Test
    public void whenGetByExistingName_thenBrandShouldBeFound() throws ResourceNotFoundException {
        Brand brand = new Brand("DELL", "path");
        Mockito.when(brandRepository.findByName("DELL")).thenReturn(Optional.of(brand));

        BrandDto dell = new BrandDto();
        Mockito.when(modelMapper.map(brand, BrandDto.class)).thenReturn(dell);

        BrandDto foundedBrand = brandService.getByName("DELL");
        assertThat(foundedBrand).isNotNull();
    }

    @Test
    public void whenGetByNoExistingName_thenExceptionShouldBeThrown() throws ResourceNotFoundException {
        String noExistingName = "noExistingName";

        exceptionRule.expect(ResourceNotFoundException.class);
        exceptionRule.expectMessage("Brand with name: " + noExistingName + " not found");

        brandService.getByName(noExistingName);
    }

    // Create new brand
    @Test
    public void whenCreateBrandWithEmptyName_thenExceptionShouldBeThrown() throws BadRequestException {
        exceptionRule.expect(BadRequestException.class);
        exceptionRule.expectMessage("Brand's name is required");

        BrandDto invalidBrand = new BrandDto();
        brandService.create(invalidBrand);
    }

    @Test
    public void whenCreateValidBrand_thenBrandShouldBeCreated() throws BadRequestException {
        BrandDto newBrandDto = new BrandDto("Asus", "logo/path");
        BrandDto createdBrandDto = new BrandDto(1, "Asus", "logo/path");
        Brand newBrand = new Brand();
        Brand createdBrand = new Brand();

        Mockito.when(modelMapper.map(newBrandDto, Brand.class)).thenReturn(newBrand);
        Mockito.when(brandRepository.save(newBrand)).thenReturn(createdBrand);
        Mockito.when(modelMapper.map(createdBrand, BrandDto.class)).thenReturn(createdBrandDto);

        BrandDto result = brandService.create(newBrandDto);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Asus");
        assertThat(result.getLogoImagePath()).isEqualTo("logo/path");
    }

    // Update brand
    @Test
    public void whenUpdateBrandWithNoExistingId_thenExceptionShouldBeThrown()
            throws ResourceNotFoundException, BadRequestException {
        int noExistingId = 1;
        Mockito.when(brandRepository.findById(noExistingId)).thenReturn(Optional.empty());

        exceptionRule.expect(ResourceNotFoundException.class);
        exceptionRule.expectMessage("Brand with id: " + noExistingId + " not found");

        brandService.update(noExistingId, new BrandDto());
    }

    @Test
    public void whenUpdateBrandWithDuplicateName_thenExceptionShouldBeThrown()
            throws ResourceNotFoundException, BadRequestException {
        String duplicateName = "abc";
        BrandDto duplicateNameBrand = new BrandDto(duplicateName, null);
        Brand existingBrand = new Brand("abc", "path");

        Mockito.when(brandRepository.findById(1)).thenReturn(Optional.of(existingBrand));
        Mockito.when(brandRepository.findByName("abc")).thenReturn(Optional.of(existingBrand));

        exceptionRule.expect(BadRequestException.class);
        exceptionRule.expectMessage("Brand's name: " + duplicateName + " is already existed");

        brandService.update(1, duplicateNameBrand);
    }

    @Test
    public void whenUpdateValidBrand_thenBrandShouldBeUpdated()
            throws ResourceNotFoundException, BadRequestException {
        Integer brandId = 1;
        BrandDto newBrandData = new BrandDto();
        BrandDto updatedBrandDto = new BrandDto("new name", "new path");
        Brand existingBrand = new Brand();
        Brand updatedBrand = new Brand();

        Mockito.when(brandRepository.findById(brandId)).thenReturn(Optional.of(existingBrand));
        Mockito.when(brandRepository.findByName("new name")).thenReturn(Optional.empty());
        Mockito.when(brandRepository.save(existingBrand)).thenReturn(updatedBrand);
        Mockito.when(modelMapper.map(updatedBrand, BrandDto.class)).thenReturn(updatedBrandDto);

        BrandDto result = brandService.update(brandId, newBrandData);
        assertThat(result.getName()).isEqualTo("new name");
        assertThat(result.getLogoImagePath()).isEqualTo("new path");
    }

    @Test
    public void whenDeleteBrand_thenBrandShouldBeDeleted() {
        Integer brandId = 1;
        Mockito.when(brandRepository.findById(brandId)).thenReturn(Optional.of(new Brand()));

        brandService.deleteById(brandId);

        Mockito.verify(brandRepository, Mockito.times(1)).deleteById(brandId);
    }

}
