# Задача 2. Долой пробелы
## Описание
Помните вы писали программу, которая удаляет лишние пробелы в введенной пользователем строке? Эта задача будет похожа.

Необходимо написать решение, включающее в себя клиент и сервер. Сервер умеет удалять из любых строк все пробельные символы. Клиент должен "продемонстрировать" функциональность сервера, используя его

## Работа программы
1. Клиент бесконечно просит пользователя вводить строки с пробелами
1. Каждая строка передается на сервер
1. Сервер читает все, что ему передали, удаляет пробельные символы и результат отправляет обратно
1. Клиент отображает результат
1. Если пользователь вводит end - клиент завершается

### Фича
* Если клиент ввёл off - клиент и сервер завершают работу