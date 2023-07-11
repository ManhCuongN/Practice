package com.example.demo.Discount;

import com.example.demo.Category.CategoryDTO;
import com.example.demo.Category.CategoryEntity;
import com.example.demo.Product.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DiscountService {

    private final IDiscountRepository discountRepository;
    public DiscountService(IDiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public DiscountEntity createDiscount(DiscountDTO discountDTO) {
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setName(discountDTO.getName());
        discountEntity.setDescription(discountDTO.getDescription());
        discountEntity.setDiscount_percent(discountDTO.getDiscount_percent());
        discountEntity.setActive(discountDTO.isActive());
        return discountRepository.save(discountEntity);
    }

    public  DiscountEntity getDiscountById(UUID idDiscount) {
        return discountRepository.findById(idDiscount).orElse(null);
    }

    public DiscountEntity updateDiscount(UUID idDiscount, DiscountDTO discountDTO) {
        DiscountEntity existingDiscount = discountRepository.findById(idDiscount).orElse(null);
        if (existingDiscount == null) {
            return null;
        }
        existingDiscount.setName(discountDTO.getName());
        existingDiscount.setDescription(discountDTO.getDescription());
        existingDiscount.setDiscount_percent(discountDTO.getDiscount_percent());
        existingDiscount.setActive(discountDTO.isActive());

        return discountRepository.save(existingDiscount);
    }

    public DiscountEntity deleteDiscount(UUID idDiscount) {
        Optional<DiscountEntity> discountOptional = discountRepository.findById(idDiscount);
        if (discountOptional.isPresent()) {
            DiscountEntity discount = discountOptional.get();
            discount.setIs_deleted(true);
            return discountRepository.save(discount);
        }
        return null;
    }

    public void destroyDiscount(UUID idDiscount) {
        discountRepository.deleteById(idDiscount);
    }

}
