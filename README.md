<img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" />

[![Actions Status](https://github.com/CapitalCoding/events-app/workflows/android/badge.svg)](https://github.com/CapitalCoding/events-app/actions)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.72-blue.svg)](http://kotlinlang.org/)
[![AGP](https://img.shields.io/badge/AGP-4.0.0-blue)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.5-blue)](https://gradle.org)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)


Meu principal objetivo foi atingir um baixo acoplamento entre os componentes com a separação das camadas e aplicando princípios RUDT que significa que nossos cases devem estar simples de:
- Ler (Read)
- Atualizar (Update)
- Debugar (Debug)
- Testar (Test (Unit & UI))

<img src="https://diogomoreno.com/wp-content/uploads/2020/12/clean.png"/>

O projeto é composto por:
- Arquitetura limpa (de uma perspectiva de simplificar funcionalidades modulando-as).
- DI (Dagger 2, agora Dagger Hilt)
- Fácil para testar, atualizar, debugar e testar

Artigos de princípios aplicados no projeto
-

[The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

[ViewModel Android, Como Utilizar Este Componente de Arquitetura](https://www.thiengo.com.br/viewmodel-android-como-utilizar-este-componente-de-arquitetura)

[Introdução em modularização de App](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e)

[Administração das dependências do Gradle com Kotlin (buildSrc)](https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28)

License
-

    Copyright 2020 Diogo Moreno

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this project except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

