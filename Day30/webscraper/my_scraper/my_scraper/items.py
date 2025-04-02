# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy

class QuoteItem(scrapy.Item):
    title = scrapy.Field()
    price= scrapy.Field()
    availability = scrapy.Field()
    rating = scrapy.Field()
    category= scrapy.Field()
