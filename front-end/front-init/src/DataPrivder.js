import { fetchUtils } from "react-admin";
import simpleRestProvider from "ra-data-simple-rest";

const apiUrl = "http://localhost:8080/api";

const httpClient = fetchUtils.fetchJson;

const customDataProvider = {
  ...simpleRestProvider(apiUrl, httpClient),

  getList: async (resource, params) => {
    const { page, perPage } = params.pagination;
    const url = `${apiUrl}/${resource}?page=${page - 1}&size=${perPage}`;

    const { json } = await httpClient(url);

    // Extrae el array de datos de la propiedad 'content'
    const data = json.content || [];

    return {
      data,
      total: json.totalElements || data.length, // Usa totalElements del backend
    };
  },
};

export const dataProvider = customDataProvider;