package com.amazons3.amazon.payloads;

import com.amazonaws.services.s3.AmazonS3;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayloadClass {

    String object;

    String esn;

    AmazonS3 client;

    String bucket;
}
