import React from 'react';
import {
    List,
    Datagrid,
    TextField,
    NumberField,
    DateField,
    EmailField,
    FunctionField
  } from 'react-admin';
  
const ClientList = (props) => (
    <List {...props} title="Clientes">
      <Datagrid rowClick="edit" bulkActionButtons={false}>
        <NumberField source="id" emptyText="-" />
        <TextField source="name" label="Nombre" emptyText="-" />
        <TextField source="lastName" label="Apellido" emptyText="-" />
        
        <DateField 
          source="birthDate" 
          label="Año de nacimiento" 
          emptyText="-"
          locales="es-AR"
          options={{ year: 'numeric', month: '2-digit', day: '2-digit' }}
        />
        
        <EmailField source="email" emptyText="-" />
        <TextField source="cuit" label="CUIT" emptyText="-" />
        
        <FunctionField
          label="Telefono"
          source="phoneNumber"
          render={record => record.phoneNumber || '-'}
        />
        
        <FunctionField
          label="Dirección"
          source="address"
          render={record => record.address || '-'}
        />
      </Datagrid>
    </List>
  );

export default ClientList;