import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { S3Client, PutObjectCommand } from "@aws-sdk/client-s3";
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class S3Service {

  private bucket: S3Client;

  constructor(private http: HttpClient) {
    this.bucket = new S3Client(
      {
        credentials: {
          accessKeyId: environment.AWS_ACCESS_KEY_ID,
          secretAccessKey: environment.AWS_SECRET_ACCESS_KEY,
        },
        region: environment.AWS_REGION,
      }
    );
   }


   async uploadFile(file: File) {

    const params = {
      Bucket: 'reciperylist',
      Key: file.name,
      Body: file,
      ACL: 'public-read',
      ContentType: file.type
    };

    try {
      const response = await this.bucket.send(new PutObjectCommand(params));
      console.log("SUCCESS", response);
    } catch(error) {
      console.log("FAILURE", error);
    }

  }
}
