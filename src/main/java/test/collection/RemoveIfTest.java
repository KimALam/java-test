package test.collection;

import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class RemoveIfTest {
    public static void main(String[] args) {
        List<SearchProductV1DTO> list = LongStream.range(1, 10)
                .mapToObj(i -> new SearchProductV1DTO(i))
                .collect(toList());
        Set<Long> noSet = new HashSet<>();
        noSet.add(3L);
        noSet.add(5L);
        System.out.println("origin : " + list);

        List<SearchProductV1DTO> result = getProductsRemovingRedundant(list, noSet);
        System.out.println("result : " + result);
    }

    private static List<SearchProductV1DTO> getProductsRemovingRedundant(List<SearchProductV1DTO> searchProducts,
                                                                  Set<Long> productNos) {
//        return searchProducts.stream()
//                .filter(x -> !productNos.contains(x.getProductNo()))
//                .collect(toList());
        searchProducts.removeIf(x -> productNos.contains(x.getProductNo()));
        return searchProducts;
    }

    private static class SearchProductV1DTO {
        private Long no;

        public SearchProductV1DTO(Long no) {
            this.no = no;
        }

        public Long getProductNo() {
            return no;
        }

        @Override
        public String toString() {
            return "{no=" + no + '}';
        }
    }
}
