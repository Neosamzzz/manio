package com.proj.manio.VO;

import lombok.Data;

@Data
public class HomepageVO {
    Integer categoryId;
    String categoryName;
    String imageUrl;

    Integer productIdFirst;
    String productNameFirst;
    String productCoverImgFirst;

    Integer productIdSecond;
    String productNameSecond;
    String productCoverImgSecond;

    Integer productIdThird;
    String productNameThird;
    String productCoverImgThird;

    Integer productIdFouth;
    String productNameFouth;
    String productCoverImgFouth;
}
