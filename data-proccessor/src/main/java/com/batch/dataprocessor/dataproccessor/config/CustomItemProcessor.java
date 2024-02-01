package com.batch.dataprocessor.dataproccessor.config;

import com.batch.dataprocessor.dataproccessor.model.ProductDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<ProductDataDTO, ProductDataDTO> {

    Logger logger = LoggerFactory.getLogger(CustomItemProcessor.class);

    @Override
    public ProductDataDTO process(ProductDataDTO item) throws Exception {
        // transformation logic
        try {
            Double discount = item.getDiscount();
            Double discountedPrice = (discount / 100) * item.getPrice();
            item.setDiscountedPrice(item.getPrice() - discountedPrice);
        }catch (Exception e) {
            logger.error("exception occurred while parsing");
            e.printStackTrace();

        }

        return item;
    }
}
