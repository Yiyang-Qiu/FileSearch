package com.FileSearch.jpa.elasticsearch;

public class FileSearchConstant {
    public static final String mapping = "PUT /filesearch_index\n" +
            "{\n" +
            "  \"settings\": {\n" +
            "    \"number_of_shards\": 3,\n" +
            "    \"number_of_replicas\": 1\n" +
            "  },\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"fileId\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"fileName\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"fileType\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"fileSize\": {\n" +
            "        \"type\": \"long\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"createdTime\": {\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"modifiedTime\": {\n" +
            "        \"type\": \"date\"\n" +
            "      },\n" +
            "      \"filePath\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"standard\"\n" +
            "      },\n" +
            "      \"isPublic\": {\n" +
            "        \"type\": \"boolean\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
