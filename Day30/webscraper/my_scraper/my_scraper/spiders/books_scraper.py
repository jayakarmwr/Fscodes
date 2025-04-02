import scrapy
import pymongo

class BooksSpider(scrapy.Spider):
    name = 'books'
    start_urls = ['https://books.toscrape.com']

    def __init__(self):
        # Connect to MongoDB
        self.client = pymongo.MongoClient("mongodb://localhost:27017/")
        self.db = self.client["bookstore"]
        self.collection = self.db["books"]

    def parse(self, response):
        books = response.css('article.product_pod')
        for book in books:
            item = {
                "title": book.css('h3 a::attr(title)').get(),
                "price": book.css('p.price_color::text').get(),
                "availability": "In" if "In stock" in book.css('p.instock::text').getall() else "Out",
                "rating": book.css('p::attr(class)').get().split()[-1],
                "category": "Books"
            }
            
            self.collection.insert_one(item)  # Store data in MongoDB
            yield item

        # Follow pagination
        next_page = response.css('li.next a::attr(href)').get()
        if next_page:
            yield response.follow(next_page, callback=self.parse)