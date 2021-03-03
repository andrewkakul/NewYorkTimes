# NewYorkTimes
Language: Kotlin

Architecture: MVVM, Single Activity

Libraries:
- Retrofit 

    Работа с сетью, а непосредственно с API New York Times, была реализована с помощью библиотеки Retrofit. 
    Ведь он является незаменимым инструментом для работы с API в клиент-серверных приложениях.
- Navigation Architecture Component 

    Решил использовать данную библиотеку, так как она упрощает осуществление навигациив приложении.
    Благодаря ей в приложении реализована архитектура Single Activity.
- Coroutines

  Корутины использовал для работы с сетью.
  
Short description:          
Когда пользователь запускает приложение он попадает на первый экран приложения (CategoryFragment). На нем представлен список категорий по которым пользователь может искать статьи.
Список состоит из 7 категорий, его вывод на экран был реализован с помощью RecyclerView. При нажатии на категорию пользователь попадает на второй экран(ArticlesActivity).
На нем изображенны все статьи найденные по данной категории. Вывод также реализован с помощью RecyclerView. При нажатии на статью открывается третий экран(DetailFragmnet), на нем открывается статья.
Вывод статьи реализован с помощью WebView.
 
