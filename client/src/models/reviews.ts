export interface Review{
    title: string;
    rating: string;
    byline: string;
    headline: string;
    summary: string;
    reviewURL: string;
    image: string;
    commentCount: number;
}

export interface MovieComment{
    title:string;
    name:string;
    rating:number;
    comment:string;
}
