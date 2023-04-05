import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MovieComment, Review } from 'src/models/reviews';

@Injectable({
  providedIn: 'root'
})
export class SearchReviewService {

  private URI: string = "/api";   

  constructor(private httpClient: HttpClient) { }

  getReviews(query: string){
    let params = new HttpParams()
                  .append("query",query)
  return this.httpClient.get<{reviews:Review[]}>(this.URI + "/search",{params:params})
  }

  postComment(comment:MovieComment){
    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    let payload=["title="+comment.title,"name="+comment.name,"rating="+comment.rating,"comment="+comment.comment].join("&")
    return this.httpClient.post(this.URI +"/comment", payload,{headers});
  }
}
