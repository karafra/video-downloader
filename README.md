<div id="top"></div>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![codecov][codecov-shield]][codecov-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/karafra/spring-boot">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Spring boot downloader</h3>

  <p align="center">
    Utility for easy download of videos directly from CDN
    <br />
    <a href="https://karafra.github.io/spring-boot/"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/karafra/spring-boot">View Demo</a>
    ·
    <a href="https://github.com/karafra/spring-boot/issues">Report Bug</a>
    ·
    <a href="https://github.com/karafra/spring-boot/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)
Simple website used for downloading videos from various websites without storing them on server side at all (directly from CDM of platform)
<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Java](https://www.oracle.com/java/technologies/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Mockito](https://site.mockito.org/)
* [Thymeleaf](https://www.thymeleaf.org/)
* [Docker](https://www.thymeleaf.org/)
* [Jsoup](https://jsoup.org/)
* [Bootstrap](https://getbootstrap.com)
* [JQuery](https://jquery.com)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

For running this project in development mode Java 11 and maven are needed
* maven
  ```sh
  sudo apt update
  sudo apt install maven
  ```
  You can verify installation by typing
  ```sh
  mvn -version
  ```
* Java
  ```sh
  sudo apt update
  sudo apt-get install openjdk-11-jdk
  ```
  You can verify installation by typing
  ```sh
  java -version
  ```

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/karafra/spring-boot.git
   ```
2. Install packages
   ```sh
   mvn clean install
   ```
4. Start project
   ```sh
   mvn spring-boot:run
   ```

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

As of now this downloader suppotrs only downloading from bitchute, simply copy link of bitchute video and paste it, then on the download screen click download and wait for download to start.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Add option to download from bitchute
- [ ] Extend this skeleton to enable downloads from websites like [Youtube](https://www.youtube.com/) and [Twitter](https://twitter.com/)
  - [ ] Add option to download videos from TV archives such as [Voyo](https://voyo.markiza.sk/) or [Tv Joj archive](https://voyo.markiza.sk/)

See the [open issues](https://github.com/karafra/spring-boot/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Apache License 2.0. See [LICENSE](./LICENSE) for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Your Name - [@Karafro](https://twitter.com/Karafro) - dariusKralovic@protonmail.com

Project Link: [https://github.com/karafra/spring-boot](https://github.com/karafra/spring-boot)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* This project acknowledges [SaveTheVid](https://www.savetweetvid.com/) as it's UI was after refactors used in this project.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/karafra/spring-boot.svg?style=for-the-badge
[contributors-url]: https://github.com/karafra/spring-boot/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/karafra/spring-boot.svg?style=for-the-badge
[forks-url]: https://github.com/karafra/spring-boot/network/members
[stars-shield]: https://img.shields.io/github/stars/karafra/spring-boot.svg?style=for-the-badge
[stars-url]: https://github.com/karafra/spring-boot/stargazers
[issues-shield]: https://img.shields.io/github/issues/karafra/spring-boot.svg?style=for-the-badge
[issues-url]: https://github.com/karafra/spring-boot/issues
[license-shield]: https://img.shields.io/github/license/karafra/spring-boot.svg?style=for-the-badge
[license-url]: https://github.com/karafra/spring-boot/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.GIF
[codecov-shield]: https://img.shields.io/codecov/c/github/karafra/spring-boot/master?style=for-the-badge&token=JT7B2MF504
[codecov-url]: https://codecov.io/gh/karafra/spring-boot
