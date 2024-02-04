create table if not exists batch_processing.products(

    productId int primary key,
    title varchar(200),
    description varchar(200),
    price varchar(10),
    discount varchar(5),
    discountPrice varchar(10)
);