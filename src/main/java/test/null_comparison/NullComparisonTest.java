package test.null_comparison;

class NullComparisonTest {
    public static void main(String[] args) {
        Long v0 = null;
        Long v1 = 6L;
        Long v2 = 0L;
        Long v3 = -3L;
        System.out.println(Long.signum(v0));
        System.out.println(Long.signum(v1));
        System.out.println(Long.signum(v2));
        System.out.println(Long.signum(v3));
        if (v1 < 0) {
            System.out.println("less than 0");
        } else {
            System.out.println("greater than or equal to");
        }
    }

    public void setSectionQuery(MultiValueMap queryMap, CosmosField cosmosField, Long minValue, Long maxValue) {
        if ((minValue == null && maxValue == null) || (minValue <= 0 && maxValue <= 0)) {
            return;
        }

        StringBuffer tempQuery = new StringBuffer();
        tempQuery.append(cosmosField.getField());
        tempQuery.append(FilterCondition.OR.getDelimiter());

        if (minValue != null && minValue > 0) {
            tempQuery.append(minValue);
        }

        tempQuery.append(FilterCondition.RANGE.getDelimiter());
        if (maxValue != null && maxValue > 0) {
            tempQuery.append(maxValue);
        }

        queryMap.add(CosmosParamType.FILTER.getParameter(), tempQuery.toString());
    }

    private static class MultiValueMap {
        public void add(String parameter, String toString) {
        }
    }

    private static class CosmosField {
        public String getField() {
            return null;
        }
    }

    private enum CosmosParamType {
        FILTER;

        public String getParameter() {
            return null;
        }
    }

    private enum FilterCondition {
        RANGE,
        OR;

        public String getDelimiter() {
            return null;
        }
    }
}
