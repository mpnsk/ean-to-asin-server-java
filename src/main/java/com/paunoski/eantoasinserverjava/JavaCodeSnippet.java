package com.paunoski.eantoasinserverjava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
 * This class shows how to make a simple authenticated call to the
 * Amazon Product Advertising API.
 *
 * See the README.html that came with this sample for instructions on
 * configuring and running the sample.
 */
@Component
public class JavaCodeSnippet {

    /*
     * Your Access Key ID, as taken from the Your Account page.
     */
    @Value("${amazon-product-advertising-api.access-key}")
    private String ACCESS_KEY_ID;

    /*
     * Your Secret Key corresponding to the above ID, as taken from the
     * Your Account page.
     */
    @Value("${amazon-product-advertising-api.secret-key}")
    private String SECRET_KEY;

    /*
     * Use the end-point according to the region you are interested in.
     */
    @Value("${amazon-region-endpoint}")
    private String ENDPOINT;


    @Value("${amazon-associate-tag}")
    private String ASSOCIATE_TAG;

    String getUrl(String ean) {
        /*
         * Set up the signed requests helper.
         */
        SignedRequestsHelper helper;

        try {
            helper = SignedRequestsHelper.getInstance(ENDPOINT, ACCESS_KEY_ID, SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String requestUrl = null;

        Map<String, String> params = new HashMap<>();

        params.put("Service", "AWSECommerceService");
        params.put("Operation", "ItemLookup");
        params.put("AWSAccessKeyId", ACCESS_KEY_ID);
        params.put("AssociateTag", ASSOCIATE_TAG);
        params.put("ItemId", ean);
        params.put("IdType", "EAN");
        params.put("ResponseGroup", "ItemAttributes");
        params.put("SearchIndex", "All");

        requestUrl = helper.sign(params);
        return requestUrl;
    }
}
