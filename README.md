# Meli Android challenge

Android App built with Jetpack Compose, Hilt for dependency injection, and Ktor Android client for networking.

All data provided by [Meli API](https://developers.mercadolibre.com.ar/es_ar/api-docs-es).

## Results

<img
    alt="docs_01"
    height="400"
    src="docs/docs_01.png" />
<img
    alt="docs_02"
    height="400"
    src="docs/docs_02.png" />
<img
    alt="docs_03"
    height="400"
    src="docs/docs_03.png" />
<img
    alt="docs_04"
    height="400"
    src="docs/docs_04.png" />

## Architecture

App architecture follows clean architecture and domain driven design (DDD), using the 
recommended MVVM pattern. It consists of 3 main layers: UI, domain and data.

### Data

Here are the implementations responsible for communicating with the server, receiving the information and converting it into a data transfer object (DTO).

### Domain

Here are the domain entities and repository contracts, which communicates with the data layer,
map the DTO to a domain entity, and handle any possible exceptions. Any repository implementation belongs to the data layer.

### UI

The presentation layer, composed by UI components, screens, and view models.

- **Search screen**: It has a text field that triggers the search request as the user types. After requesting and obtaining data, the list of results is presented, which requests another page of information as the user scrolls (pagination).
    
    If an error ocurred during the request, or if the results are empty, a text message is displayed indicating what happended.

- **Detail screen**: It receives the ID of the item to request its complete information. The view model request data to the repository and then display the result.
    
    It has an icon button to share item source url, and a pager to display item's pictures.

    If an error ocurred during the request, a text message is displayed indicating what happended.

### Error Handling

*Exceptions are thrown, Failures are returned.*

When an `Exception` is thrown by the data layer, a repository catch it and convert it to `Failure`,
which is returned to the UI layer using `Either` type. Then, the UI can use this `Failure` to change its state or perform some action.

## Testing

### Unit tests

**MeliServiceImplTest** tests the communication with the Ktor client and data serialization.

**MeliRepositoryImplTest** tests the communication with the network service, the mapping between network entities and domain entities, and error handling.

### Instrumented tests

**MainActivityTest** tests the complete user flow, using a fake repository implementation in order to have dummy data.
It uses Jetpack Compose Testing API to interact with elements.
