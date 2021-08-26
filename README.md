# N26 Coding Challenge

## Description

We would like to have a RESTful API for our statistics. The main use case for the
API is to calculate realtime statistics for the last 60 seconds of transactions.
The API needs the following endpoints:
* POST /transactions – called every time a transaction is made.
* GET /statistics – returns the statistic based of the transactions of the last 60
seconds.
* DELETE /transactions – deletes all transactions.

