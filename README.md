# Kütüphane Yönetim Sistemi

## Proje Tanımı

Kütüphane Yönetim Sistemi, modern mikroservis mimarisi kullanılarak geliştirilmiş, kapsamlı bir kütüphane yönetim platformudur. Bu sistem, kütüphanelerin günlük operasyonlarını dijitalleştirerek, kitap ödünç alma, rezervasyon, üye yönetimi, personel yönetimi ve ceza takibi gibi işlemleri merkezi bir platform üzerinden yönetmeyi sağlar.

### Çözülen Problemler

- **Merkezi Kütüphane Yönetimi**: Tüm kütüphane operasyonlarının tek bir sistem üzerinden yönetilmesi
- **Ölçeklenebilir Mimari**: Mikroservis mimarisi sayesinde her servisin bağımsız olarak ölçeklendirilebilmesi
- **Yüksek Erişilebilirlik**: Servislerin birbirinden bağımsız çalışması sayesinde sistemin daha dayanıklı olması
- **Güvenli Erişim**: OAuth2 ve Keycloak entegrasyonu ile güvenli kimlik doğrulama ve yetkilendirme
- **Asenkron İşlemler**: Kafka ile event-driven mimari sayesinde performanslı ve esnek sistem yapısı

---

## Kullanılan Teknolojiler

### Framework ve Platformlar

- **Java 21** - Programlama dili
- **Spring Boot 3.5.6/3.5.7** - Uygulama framework'ü
- **Spring Cloud 2025.0.0** - Mikroservis araçları
- **Maven** - Bağımlılık yönetimi ve build aracı

### Kütüphaneler ve Araçlar

- **Spring Data JPA** - Veritabanı erişim katmanı
- **Spring Web** - RESTful API geliştirme
- **Spring Security** - Güvenlik ve kimlik doğrulama
- **Spring Cloud Gateway** - API Gateway
- **Netflix Eureka** - Service Discovery
- **Spring Cloud Config** - Merkezi yapılandırma yönetimi
- **Spring Cloud Stream** - Mesajlaşma entegrasyonu
- **Springdoc OpenAPI** - API dokümantasyonu

### Mimari Desenler ve Yaklaşımlar

- **Onion Architecture** - Katmanlı mimari, domain'in merkezde olduğu yapı
- **Domain-Driven Design (DDD)** - Domain odaklı tasarım yaklaşımı
- **CQRS (Command Query Responsibility Segregation)** - Komut ve sorgu ayrımı
- **Aggregate Root Pattern** - Domain aggregate'lerinin yönetimi
- **Value Objects** - Immutable değer nesneleri
- **Domain Events** - Domain olayları ve event sourcing desteği
- **Repository Pattern** - Veri erişim soyutlaması
- **Outbox Pattern** - Transactional outbox ile event tutarlılığı

### Veritabanı

- **PostgreSQL 16** - İlişkisel veritabanı (her servis için ayrı veritabanı)

### Mesajlaşma ve Event Streaming

- **Apache Kafka 3.8.0** - Event-driven mimari için mesajlaşma platformu
- **Kafka UI** - Kafka yönetim arayüzü

### Kimlik Doğrulama ve Yetkilendirme

- **Keycloak 24.0.5** - Kimlik ve erişim yönetimi
- **OAuth2** - Yetkilendirme protokolü

### Containerization ve Deployment

- **Docker** - Containerization
- **Docker Compose** - Çoklu container yönetimi

---

## Özellikler

### Ana Özellikler

1. **Kitap Yönetimi**

   - Kitap ekleme, güncelleme, silme ve sorgulama
   - Kitap detay bilgileri yönetimi

2. **Yazar Yönetimi**

   - Yazar bilgileri CRUD işlemleri
   - Yazar- kitap ilişkileri

3. **Yayınevi Yönetimi**

   - Yayınevi bilgileri yönetimi

4. **Üye Yönetimi**

   - Üye kayıt, güncelleme ve silme
   - Üye bilgileri sorgulama

5. **Personel Yönetimi**

   - Personel kayıt ve yönetimi
   - Personel bilgileri güncelleme

6. **Ödünç Verme İşlemleri**

   - Kitap ödünç verme işlemleri
   - Ödünç verme kayıtları

7. **Rezervasyon Sistemi**

   - Kitap rezervasyon işlemleri

8. **Ceza Yönetimi**
   - Geç teslim cezaları takibi

### Proje Mimarisine Genel Bakış

#### Mimari Desenler

- **Onion Architecture**: Katmanlı mimari yapısı ile domain'in merkezde olduğu, bağımlılıkların dışa doğru yönlendiği mimari
- **Domain-Driven Design (DDD)**: İş mantığının domain modeli etrafında organize edildiği, domain uzmanları ile geliştiriciler arasında ortak dil (Ubiquitous Language) kullanımı
- **CQRS Pattern**: Command ve Query ayrımı ile performans optimizasyonu ve okuma/yazma işlemlerinin bağımsız ölçeklendirilmesi
- **Aggregate Root Pattern**: Domain aggregate'lerinin tutarlılığını sağlayan root entity'ler
- **Value Objects**: Immutable değer nesneleri ile domain mantığının kapsüllemesi
- **Domain Events**: Domain içindeki önemli olayların yayınlanması ve event-driven mimari desteği
- **Repository Pattern**: Domain ve infrastructure katmanları arasında soyutlama sağlayan repository pattern
- **Outbox Pattern**: Veritabanı ve mesajlaşma tutarlılığı için transactional outbox pattern

#### Sistem Mimarisi

- **Event-Driven Architecture**: Kafka ile asenkron mesajlaşma
- **API Gateway**: Merkezi API yönetimi ve routing
- **Service Discovery**: Otomatik servis keşfi (Eureka)
- **Centralized Configuration**: Merkezi yapılandırma yönetimi (Config Server)
- **BFF (Backend for Frontend)**: Frontend için özelleştirilmiş API katmanı
- **OAuth2 Security**: Güvenli API erişimi
- **API Documentation**: Swagger/OpenAPI dokümantasyonu

---

## Kurulum Talimatları

### Gereksinimler

- **Java 21** veya üzeri
- **Maven 3.6+**
- **Docker** ve **Docker Compose**
- **Git**

### Adım 1: Projeyi Klonlayın

```bash
git clone <repository-url>
cd microservice-library
```

### Adım 2: Docker Container'larını Başlatın

Proje kök dizininde bulunan `docker-compose.yml` dosyası ile tüm gerekli servisleri (PostgreSQL, Kafka, Keycloak) başlatın:

```bash
docker-compose up -d
```

Bu komut şunları başlatır:

- PostgreSQL veritabanları (her servis için ayrı)
- Apache Kafka ve Kafka UI
- Keycloak (kimlik doğrulama servisi)

### Adım 3: Servisleri Sırayla Başlatın

Servislerin başlatılma sırası önemlidir. Aşağıdaki sırayı takip edin:

#### 1. Config Server'ı Başlatın

```bash
cd config-server
./mvnw spring-boot:run
```

veya Windows'ta:

```bash
cd config-server
mvnw.cmd spring-boot:run
```

#### 2. Discovery Server'ı Başlatın

```bash
cd discovery-server
./mvnw spring-boot:run
```

veya Windows'ta:

```bash
cd discovery-server
mvnw.cmd spring-boot:run
```

#### 3. Diğer Servisleri Başlatın

Her servis için ayrı terminal penceresi açarak:

```bash
# Author Service
cd author-service
./mvnw spring-boot:run

# Book Service
cd book-service
./mvnw spring-boot:run

# Publisher Service
cd publisher-service
./mvnw spring-boot:run

# Member Service
cd member-service
./mvnw spring-boot:run

# Staff Service
cd staff-service
./mvnw spring-boot:run

# Loan Service
cd loan-service
./mvnw spring-boot:run

# Reservation Service
cd reservation-service
./mvnw spring-boot:run

# Fine Service
cd fine-service
./mvnw spring-boot:run

# Gateway Server
cd gateway-server
./mvnw spring-boot:run

# BFF Service
cd bff-service
./mvnw spring-boot:run
```

Windows'ta `./mvnw` yerine `mvnw.cmd` kullanın.

---

## Çalıştırma Talimatları

### Servisleri Başlatma

1. **Docker servislerinin çalıştığından emin olun:**

   ```bash
   docker-compose ps
   ```

2. **Servisleri yukarıdaki sırayla başlatın**

3. **Servislerin durumunu kontrol edin:**
   - Discovery Server Dashboard: http://localhost:8761
   - Kafka UI: http://localhost:8079
   - Keycloak Admin Console: http://localhost:8585

### Gerekli Komutlar

#### Tüm Servisleri Build Etme

```bash
# Proje kök dizininde
mvn clean install
```

#### Tek Bir Servisi Build Etme

```bash
cd <service-name>
mvn clean install
```

#### Docker Container'larını Durdurma

```bash
docker-compose down
```

#### Docker Container'larını ve Volume'ları Temizleme

```bash
docker-compose down -v
```

### Environment Ayarları

Servislerin yapılandırmaları `configurations/` dizininde bulunmaktadır. Her servis için `application.yml` ve `application-dev.yml` dosyaları mevcuttur.

#### Önemli Portlar

- **Discovery Server**: 8761
- **Config Server**: 8888
- **Gateway Server**: 8080 (varsayılan)
- **Kafka**: 9092 (internal), 9094 (external)
- **Kafka UI**: 8079
- **Keycloak**: 8585
- **PostgreSQL**: Her servis için farklı portlar (5433, 5436, 5437, vb.)

#### Veritabanı Bağlantıları

Veritabanı bağlantı bilgileri `docker-compose.yml` dosyasında tanımlanmıştır. Her servis kendi veritabanını kullanır:

- `book_service` - Port: 5433
- `author_service` - Port: 5436
- `publisher_service` - Port: 5437
- `member_service` - Port: 5438
- `loan_service` - Port: 5439
- `staff_service` - Port: 5441
- `reservation_service` - Port: 5442
- `fine_service` - Port: 5443

---

## API Endpoint'leri

### Gateway Server Üzerinden Erişim

Tüm API istekleri Gateway Server üzerinden yapılmalıdır. Gateway Server varsayılan olarak `http://localhost:8080` portunda çalışır.

### Author Service

**Base URL**: `/api/v1/authors`

- `POST /api/v1/authors` - Yeni yazar oluştur
- `GET /api/v1/authors/{id}` - Yazar detaylarını getir
- `PUT /api/v1/authors/{id}` - Yazar bilgilerini güncelle
- `DELETE /api/v1/authors/{id}` - Yazarı sil

### Book Service

**Base URL**: `/api/v1/books`

- `POST /api/v1/books` - Yeni kitap oluştur
- `GET /api/v1/books/{id}` - Kitap detaylarını getir
- `PUT /api/v1/books/{id}` - Kitap bilgilerini güncelle
- `DELETE /api/v1/books/{id}` - Kitabı sil

### Publisher Service

**Base URL**: `/api/v1/publishers`

- `POST /api/v1/publishers` - Yeni yayınevi oluştur
- `GET /api/v1/publishers` - Yayınevi bilgilerini getir

### Member Service

**Base URL**: `/api/v1/members`

- `POST /api/v1/members` - Yeni üye oluştur
- `GET /api/v1/members/{id}` - Üye detaylarını getir
- `PUT /api/v1/members/{id}` - Üye bilgilerini güncelle
- `DELETE /api/v1/members/{id}` - Üyeyi sil

### Staff Service

**Base URL**: `/api/v1/staffs`

- `POST /api/v1/staffs` - Yeni personel oluştur
- `GET /api/v1/staffs/{id}` - Personel detaylarını getir
- `PUT /api/v1/staffs/{id}` - Personel bilgilerini güncelle
- `DELETE /api/v1/staffs/{id}` - Personeli sil

### Loan Service

**Base URL**: `/api/v1/loans`

- `POST /api/v1/loans` - Yeni ödünç verme işlemi oluştur

### Reservation Service

**Base URL**: `/api/v1/reservations`

- `POST /api/v1/reservations` - Yeni rezervasyon oluştur

### BFF Service

**Base URL**: `/api/**`

- Tüm istekler Gateway Server'a yönlendirilir

### API Dokümantasyonu

Swagger/OpenAPI dokümantasyonuna erişmek için:

- Book Service: `http://localhost:<port>/swagger-ui.html` (eğer yapılandırılmışsa)

### Kimlik Doğrulama

Çoğu endpoint OAuth2 token gerektirir. Token almak için:

1. Keycloak Admin Console'a giriş yapın: http://localhost:8585
2. İlgili client için token alın
3. API isteklerinde `Authorization: Bearer <token>` header'ını ekleyin

---

## Proje Yapısı

```
microservice-library/
├── author-service/          # Yazar yönetimi servisi
├── book-service/            # Kitap yönetimi servisi
├── bff-service/             # Backend for Frontend servisi
├── config-server/           # Merkezi yapılandırma servisi
├── discovery-server/        # Eureka Service Discovery
├── fine-service/            # Ceza yönetimi servisi
├── gateway-server/          # API Gateway
├── loan-service/            # Ödünç verme servisi
├── member-service/          # Üye yönetimi servisi
├── publisher-service/       # Yayınevi yönetimi servisi
├── reservation-service/     # Rezervasyon servisi
├── staff-service/           # Personel yönetimi servisi
├── configurations/          # Merkezi yapılandırma dosyaları
└── docker-compose.yml       # Docker servisleri yapılandırması
```

Her servis **Onion Architecture** prensiplerine göre aşağıdaki yapıyı takip eder:

```
<service-name>/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/turkcell/
│   │   │       └── <service_name>/
│   │   │           ├── domain/           # Domain Layer (En İç Katman)
│   │   │           │   ├── model/        # Aggregate Roots, Value Objects
│   │   │           │   ├── event/        # Domain Events
│   │   │           │   ├── exception/    # Domain Exceptions
│   │   │           │   └── repository/   # Repository Interfaces (Ports)
│   │   │           ├── application/      # Application Layer
│   │   │           │   ├── command/      # Command Handlers (CQRS)
│   │   │           │   ├── query/        # Query Handlers (CQRS)
│   │   │           │   ├── dto/          # Data Transfer Objects
│   │   │           │   └── mapper/       # Domain-DTO Mappers
│   │   │           ├── infrastructure/   # Infrastructure Layer
│   │   │           │   ├── Jpa*Entity     # JPA Entities
│   │   │           │   ├── Jpa*Repository # JPA Repositories
│   │   │           │   └── *Adapter      # Repository Adapters
│   │   │           ├── interfaces/       # Interfaces Layer (En Dış Katman)
│   │   │           │   └── web/          # REST Controllers
│   │   │           ├── messaging/        # Messaging Infrastructure
│   │   │           │   ├── consumer/      # Event Consumers
│   │   │           │   ├── outbox/       # Outbox Pattern Implementation
│   │   │           │   └── relayer/      # Event Relayers
│   │   │           └── core/             # Core CQRS Abstractions
│   │   │               └── cqrs/         # Command/Query Interfaces
│   │   └── resources/
│   │       └── application.yml
│   └── test/
└── pom.xml
```

### Mimari Katmanlar Açıklaması

**Onion Architecture** prensiplerine göre bağımlılıklar dıştan içe doğru yönlenir:

1. **Domain Layer (En İç)**: İş mantığının merkezi, hiçbir dış bağımlılığı yok

   - Aggregate Roots: `Book`, `Member`, `Author` gibi ana domain entity'leri
   - Value Objects: `BookId`, `Title`, `Email` gibi immutable değer nesneleri
   - Domain Events: `BookCreated`, `BookStatusChangedEvent` gibi domain olayları
   - Domain Exceptions: `BookNotFoundException` gibi domain hataları

2. **Application Layer**: Use case'leri ve iş akışlarını yönetir

   - Command Handlers: Domain'e komut gönderen handler'lar
   - Query Handlers: Veri okuma işlemlerini yöneten handler'lar
   - DTOs: Katmanlar arası veri transferi için nesneler

3. **Infrastructure Layer**: Teknik detayları ve dış sistem entegrasyonlarını yönetir

   - JPA Entities: Veritabanı persistence için entity'ler
   - Repository Adapters: Domain repository interface'lerini JPA'ya adapte eder

4. **Interfaces Layer (En Dış)**: Dış dünya ile iletişimi sağlar
   - REST Controllers: HTTP endpoint'leri

---

## 📡 Event'ler ve Mesajlaşma

Proje, **Event-Driven Architecture** prensiplerine göre çalışır. Servisler arası iletişim Kafka üzerinden asenkron event'ler ile sağlanır.

### Domain Event'ler

Domain event'ler, domain katmanında oluşan ve iş mantığını yansıtan olaylardır. Bu event'ler Outbox Pattern kullanılarak Kafka'ya gönderilir.

#### Book Service

- **`BookCreated`** - Yeni bir kitap oluşturulduğunda yayınlanır
- **`BookStatusChangedEvent`** - Kitap durumu değiştiğinde yayınlanır (ör. AVAILABLE → CHECKED_OUT)
- **`BookCreateFailed`** - Kitap oluşturma işlemi başarısız olduğunda yayınlanır

#### Loan Service

- **`LoanCreatedEvent`** - Yeni bir ödünç verme işlemi oluşturulduğunda yayınlanır
- **`BookReturnedEvent`** - Kitap iade edildiğinde yayınlanır
- **`LoanBecameOverdueEvent`** - Ödünç verme süresi dolduğunda yayınlanır
- **`LoanDueDateExtendedEvent`** - Ödünç verme süresi uzatıldığında yayınlanır

#### Reservation Service

- **`ReservationCreatedEvent`** - Yeni bir rezervasyon oluşturulduğunda yayınlanır
- **`ReservationCancelledEvent`** - Rezervasyon iptal edildiğinde yayınlanır
- **`ReservationExpiredEvent`** - Rezervasyon süresi dolduğunda yayınlanır

#### Fine Service

- **`FineCreatedEvent`** - Yeni bir ceza oluşturulduğunda yayınlanır
- **`FineCancelledEvent`** - Ceza iptal edildiğinde yayınlanır

#### Staff Service

- **`StaffCreatedEvent`** - Yeni bir personel oluşturulduğunda yayınlanır
- **`StaffCreatedFailedEvent`** - Personel oluşturma işlemi başarısız olduğunda yayınlanır

#### Publisher Service

- **`PublisherCreatedEvent`** - Yeni bir yayınevi oluşturulduğunda yayınlanır
- **`PublisherCreatedFailedEvent`** - Yayınevi oluşturma işlemi başarısız olduğunda yayınlanır

### Integration Event'ler

Integration event'ler, servisler arası iletişim için kullanılan ve Kafka üzerinden yayınlanan event'lerdir.

#### Loan Service → Book Service

- **`LoanCreatedIntegrationEvent`**
  - **Yayınlayan**: Loan Service
  - **Tüketen**: Book Service
  - **Amaç**: Ödünç verme işlemi oluşturulduğunda Book Service'e bildirim gönderir
  - **İçerik**: `loanId`, `memberId`, `bookId`
  - **Etki**: Book Service, kitabın durumunu günceller ve mevcut kopya sayısını azaltır

#### Reservation Service → Diğer Servisler

- **`ReservationCreatedIntegrationEvent`**
  - **Yayınlayan**: Reservation Service
  - **Tüketen**: İlgili servisler
  - **Amaç**: Rezervasyon oluşturulduğunda diğer servislere bildirim
  - **İçerik**: `reservationId`, `bookId`, `memberId`, `reservedAt`, `expireAt`

### Event Akışı Örneği

```
1. Loan Service: LoanCreatedEvent (Domain Event)
   ↓
2. Loan Service: LoanCreatedIntegrationEvent (Integration Event)
   ↓ (Kafka)
3. Book Service: LoanCreatedEventConsumer
   ↓
4. Book Service: Book.markAsOnLoan() (Domain Logic)
   ↓
5. Book Service: BookStatusChangedEvent (Domain Event)
```

### Kafka Topic'leri

Event'ler aşağıdaki Kafka topic'lerine yayınlanır:

- `bookCreated-out-0` - Kitap oluşturma event'leri
- `loanEvents-out-0` - Ödünç verme event'leri
- `reservationEvents-out-0` - Rezervasyon event'leri
- `staffCreated-out-0` - Personel oluşturma event'leri
- `publisherCreated-out-0` - Yayınevi oluşturma event'leri
- `memberCreated-out` - Üye oluşturma event'leri

### Event İşleme Mekanizması

1. **Outbox Pattern**: Domain event'ler önce veritabanına (Outbox tablosuna) kaydedilir
2. **Event Relayer**: Periyodik olarak Outbox tablosunu kontrol eder ve Kafka'ya gönderir
3. **Event Consumer**: Kafka'dan event'leri alır ve ilgili handler'lara yönlendirir
4. **Transaction Guarantee**: Veritabanı transaction'ı ile event kaydı atomik olarak gerçekleşir

---

## Güvenlik

- **OAuth2 Resource Server**: Servisler OAuth2 token'ları ile korunur
- **Keycloak Integration**: Merkezi kimlik ve erişim yönetimi
- **JWT Tokens**: Güvenli token tabanlı kimlik doğrulama

---

## Notlar

- Servislerin başlatılma sırası önemlidir: Önce Config Server, sonra Discovery Server, ardından diğer servisler
- Her servis kendi PostgreSQL veritabanını kullanır
- Kafka event'leri servisler arası iletişim için kullanılır
- Gateway Server, tüm API isteklerinin giriş noktasıdır
- BFF Service, frontend uygulamaları için özelleştirilmiş bir API katmanı sağlar

---

## Lisans

Bu proje Turkcell Geleceği Yazanlar 4.5 - Java eğitimi kapsamında eğitim sonu projesi olarak geliştirilmiştir.

---

## Geliştiriciler

https://github.com/ozgur48  
https://github.com/eda-akkaya  
https://github.com/UtkuKaganYavuzdogannn  

---
