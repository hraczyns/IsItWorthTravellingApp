package com.hraczynski.isitworthtravellingapp.client.simplifiedresponseobject;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class CostsPerGroup {
    private String categoryName;
    private String average;
    private String median;
    private String note;
    private List<CostsPerGroupItem> costsPerGroupItemList = new ArrayList<>();

    public CostsPerGroup(String categoryName) {
        this.categoryName = categoryName;
    }

    public CostsPerGroup(String categoryName, String note) {
        this.categoryName = categoryName;
        this.note = note;
    }
}
