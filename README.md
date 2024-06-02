VK Message Repeating Bot

Приложение реализует бот VK, повторяющий сообщения пользователя, отправленные в сообщество

Для настройки необходимо зарегистрировать в настройках VK внешний https адрес для доступа к эндпойнту, принимающему callback со стороны портала. В нашем случае использован сервис xTunnel.

Настройка приложения выполнена с помощью параметров файла application.properties:<br>
server.port номер порта, на котором работает эндпойнт, принимающий callback
return_on.confirmation
callback.server.confirmed
access.token
portal.location=https://api.vk.com
api.version=5.236
