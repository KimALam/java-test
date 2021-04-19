package test.generic;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AssemblerTest {
    private static final Assembler assembler = new Assembler();

    public static void main(String[] args) {
        List<Product> productList = new ArrayList() {{
            add(new Product(1, "name_1"));
            add(new Product(2, "name_2"));
        }};

        Response response = assembler.toResponse(productList);
        System.out.println("product response : " + response);

        List<Brand> brandList = new ArrayList() {{
            add(new Brand(1, 10, "tag_1"));
            add(new Brand(2, 20, "tag_2"));
            add(new Brand(3, 30, "tag_3"));
        }};

        response = assembler.toResponse(brandList);
        System.out.println("brand response : " + response);
    }

    private static class Assembler {
        public Response toResponse(List<? extends ViewModel> itemList) {
            return Response.builder()
                           .total(itemList.size())
                           .data(itemList.stream().map(item -> item.toViewModel()).collect(toList()))
                           .build();
        }
    }

    @Getter
    @Builder
    @ToString
    private static class Product implements ViewModel {
        private long no;
        private String name;

        @Override
        public ProductViewModel toViewModel() {
            return ProductViewModel.builder()
                                   .productNo(String.valueOf(this.no))
                                   .productName(this.name)
                                   .build();
        }
    }

    @Getter
    @Builder
    @ToString
    private static class Brand implements ViewModel {
        private long seq;
        private int rank;
        private String tag;

        @Override
        public BrandViewModel toViewModel() {
            return BrandViewModel.builder()
                                 .sequence(String.valueOf(this.seq))
                                 .ranking(String.valueOf(this.rank))
                                 .tagName(this.tag)
                                 .build();
        }
    }

    @Getter
    @Builder
    @ToString
    private static class Response {
        private int total;
        private List<?> data;
    }

    private interface ViewModel {
        <R> R toViewModel();
    }

    @Getter
    @Builder
    @ToString
    private static class ProductViewModel {
        private String productNo;
        private String productName;
    }

    @Getter
    @Builder
    @ToString
    private static class BrandViewModel {
        private String sequence;
        private String ranking;
        private String tagName;
    }
}
