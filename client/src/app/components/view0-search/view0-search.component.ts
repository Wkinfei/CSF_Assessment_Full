import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view0-search',
  templateUrl: './view0-search.component.html',
  styleUrls: ['./view0-search.component.css']
})
export class View0SearchComponent implements OnInit{
  searchForm!:FormGroup
  movieName?:string

  constructor(private fb:FormBuilder,
              private router: Router){}
  
  ngOnInit(): void {
    this.searchForm = this.createForm();
  }

  private createForm():FormGroup{
    const grp = this.fb.group({
      name: this.fb.control<string>("ant")
    });
    return grp;
  }

  processForm(){
    // console.log(this.searchForm.value);
    const query = this.searchForm?.value['name'];
    this.router.navigate(['/search', query]);
  }
}
