package com.iamnaran.home.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id")
    val id: Long,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("category")
    val category: String,

    @SerialName("price")
    val price: Float,

    @SerialName("rating")
    val rating: String,

    @SerialName("stock")
    val stock: Int,

    @SerialName("thumbnail")
    val thumbnail: String,
)


public fun getSampleProducts(): List<Product> {
    return listOf(
        Product(
            id = 1,
            title = "Smartphone X",
            description = "High-performance smartphone with advanced features.",
            category = "Electronics",
            price = 799.99f,
            rating = "4.5",
            stock = 50,
            thumbnail = "https://example.com/smartphone_x.jpg"
        ),
        Product(
            id = 2,
            title = "Laptop Pro",
            description = "Powerful laptop for professionals and gamers.",
            category = "Electronics",
            price = 1299.99f,
            rating = "4.8",
            stock = 25,
            thumbnail = "https://example.com/laptop_pro.jpg"
        ),
        Product(
            id = 3,
            title = "Cotton T-Shirt",
            description = "Comfortable cotton t-shirt for everyday wear.",
            category = "Clothing",
            price = 24.99f,
            rating = "4.2",
            stock = 100,
            thumbnail = "https://example.com/tshirt.jpg"
        ),
        Product(
            id = 4,
            title = "Running Shoes",
            description = "Lightweight running shoes for optimal performance.",
            category = "Sports",
            price = 89.99f,
            rating = "4.6",
            stock = 60,
            thumbnail = "https://example.com/running_shoes.jpg"
        ),
        Product(
            id = 5,
            title = "Coffee Maker",
            description = "Automatic coffee maker with programmable timer.",
            category = "Appliances",
            price = 59.99f,
            rating = "4.3",
            stock = 30,
            thumbnail = "https://example.com/coffee_maker.jpg"
        ),
        Product(
            id = 6,
            title = "Book: The Art of Programming",
            description = "A comprehensive guide to programming best practices.",
            category = "Books",
            price = 39.99f,
            rating = "4.7",
            stock = 40,
            thumbnail = "https://example.com/programming_book.jpg"
        ),
        Product(
            id = 7,
            title = "Wireless Headphones",
            description = "Noise-canceling wireless headphones with long battery life.",
            category = "Electronics",
            price = 149.99f,
            rating = "4.9",
            stock = 20,
            thumbnail = "https://example.com/headphones.jpg"
        ),
        Product(
            id = 8,
            title = "Denim Jeans",
            description = "Classic denim jeans with a comfortable fit.",
            category = "Clothing",
            price = 49.99f,
            rating = "4.4",
            stock = 80,
            thumbnail = "https://example.com/jeans.jpg"
        ),
        Product(
            id = 9,
            title = "Yoga Mat",
            description = "Non-slip yoga mat for exercise and meditation.",
            category = "Sports",
            price = 29.99f,
            rating = "4.5",
            stock = 70,
            thumbnail = "https://example.com/yoga_mat.jpg"
        ),
        Product(
            id = 10,
            title = "Toaster Oven",
            description = "Multi-functional toaster oven for baking and grilling.",
            category = "Appliances",
            price = 79.99f,
            rating = "4.6",
            stock = 35,
            thumbnail = "https://example.com/toaster_oven.jpg"
        )
    )
}