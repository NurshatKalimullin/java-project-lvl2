package hexlet.code.formats;

import java.util.LinkedHashMap;
import java.util.Map;

public class Plain {

    public static String formatToStylish(LinkedHashMap<String, Object> map) {
        String result = "";

        LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!entry.getKey().startsWith("  ")) {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println(newMap);
        for (Map.Entry<String, Object> entry1 : newMap.entrySet()) {
            String key1 = entry1.getKey();

            if (key1.startsWith("- ")) {
                Object value1 = entry1.getValue();

                for (Map.Entry<String, Object> entry2 : newMap.entrySet()) {
                    String key2 = entry2.getKey();
                    Object value2 = entry2.getValue();

                    if (key1.substring(2).equals(key2.substring(2)) &&
                            entry2.getKey().startsWith("+ ")) {
                        result = result + "Property '"
                                + entry1.getKey().substring(2)
                                + "' was updated. From "
                                + formatValue(value1)
                                + " to "
                                + formatValue(value2)
                                + "\n";
                    }
                }

            }
        }
        System.out.println(newMap);
        System.out.println(result);
        return "";

//        Property 'chars2' was updated. From [complex value] to false
//        Property 'checked' was updated. From false to true
//        Property 'default' was updated. From null to [complex value]
//        Property 'id' was updated. From 45 to null
//        Property 'key1' was removed
//        Property 'key2' was added with value: 'value2'
//        Property 'numbers2' was updated. From [complex value] to [complex value]
//        Property 'numbers3' was removed
//        Property 'numbers4' was added with value: [complex value]
//        Property 'obj1' was added with value: [complex value]
//        Property 'setting1' was updated. From 'Some value' to 'Another value'
//        Property 'setting2' was updated. From 200 to 300
//        Property 'setting3' was updated. From true to 'none'


//        {
//            chars1: [a, b, c]
//            - chars2: [d, e, f]
//            + chars2: false
//                - checked: false
//                + checked: true
//                - default: null
//                + default: [value1, value2]
//            - id: 45
//                    + id: null
//                    - key1: value1
//                    + key2: value2
//            numbers1: [1, 2, 3, 4]
//            - numbers2: [2, 3, 4, 5]
//            + numbers2: [22, 33, 44, 55]
//            - numbers3: [3, 4, 5]
//            + numbers4: [4, 5, 6]
//            + obj1: {nestedKey=value, isNested=true}
//        - setting1: Some value
//        + setting1: Another value
//        - setting2: 200
//                + setting2: 300
//                - setting3: true
//                + setting3: none
//        }
    }

    private static String formatValue(Object value) {
        String result;
        if (!String.valueOf(value).equals("null") &&
                (value.getClass().getSimpleName().equals("ArrayList") ||
                value.getClass().getSimpleName().equals("LinkedHashMap"))) {
            result = "[complex value]";
        } else {
            result = String.valueOf(value);
        }
        return result;
    }
}
