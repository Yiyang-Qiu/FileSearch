package com.FileSearch.jpa.elasticsearch;

public class fileSearchConstant {
    public static final String mapping = "PUT /filesearch_index\n" +
            "{\n" +
            "  \"settings\": {\n" +
            "    \"number_of_shards\": 3, \n" +
            "    \"number_of_replicas\": 1\n" +
            "    //自定义分词器\n" +
            "  }, \n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"title\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"search_analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"content\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"search_analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"time\":{\n" +
            "        \"type\": \"date\",\n" +
            "        \"format\": \"epoch_second\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
