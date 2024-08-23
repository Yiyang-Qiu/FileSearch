package com.FileSearch.jpa.elasticsearch;

public class FileSearchConstant {
    public static final String mapping = "PUT /file_info_index\n" +
            "{\n" +
            "  \"settings\": {\n" +
            "    \"number_of_shards\": 3,\n" +
            "    \"number_of_replicas\": 1,\n" +
            "    \"analysis\": {\n" +
            "      \"analyzer\": {\n" +
            "        \"my_analyzer\":{\n" +
            "          \"tokenizer\": \"ik_max_word\",\n" +
            "          \"filter\": \"py\"\n" +
            "        },\n" +
            "        \"completion_analyzer\":{\n" +
            "          \"tokenizer\": \"keyword\",\n" +
            "          \"filter\": \"py\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"filter\": {\n" +
            "        \"py\":{\n" +
            "          \"type\": \"pinyin\",\n" +
            "          \"keep_full_pinyin\": false,\n" +
            "          \"keep_joined_full_pinyin\": true,\n" +
            "          \"keep_original\": true,\n" +
            "          \"none_chinese_pinyin_tokenize\": false,\n" +
            "          \"remove_duplicated_term\": true,\n" +
            "          \"limit_first_letter_length\": 20\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"fileId\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"fileName\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"my_analyzer\",\n" +
            "        \"search_analyzer\": \"ik_smart\"\n" +
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
            "      },\n" +
            "      \"suggestion\":{\n" +
            "        \"type\": \"completion\",\n" +
            "        \"analyzer\": \"completion_analyzer\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
