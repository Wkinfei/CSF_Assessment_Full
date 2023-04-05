import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { View0SearchComponent } from './components/view0-search/view0-search.component';
import { View1DisplayComponent } from './components/view1-display/view1-display.component';
import { View2CommentComponent } from './components/view2-comment/view2-comment.component';

const routes: Routes = [
  {path:'',component:View0SearchComponent},
  {path:'search/:query',component:View1DisplayComponent},
  {path:'comment',component:View2CommentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
