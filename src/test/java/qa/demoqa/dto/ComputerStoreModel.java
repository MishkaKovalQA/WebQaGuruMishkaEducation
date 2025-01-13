package qa.demoqa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputerStoreModel implements Serializable {

    @JsonProperty("Store")
    @SerializedName("Store")
    @Builder.Default
    private Store store = Store.builder().build();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Store {
        @Builder.Default
        private String name = "Computer City";
        @Builder.Default
        private List<ProductsItem> products = List.of(ProductsItem.builder().build());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductsItem {
        @Builder.Default
        private float price = 999.99f;
        @Builder.Default
        private String name = "Processor";
        @Builder.Default
        private boolean inStock = true;
        @Builder.Default
        private int id = 1;
    }
}
