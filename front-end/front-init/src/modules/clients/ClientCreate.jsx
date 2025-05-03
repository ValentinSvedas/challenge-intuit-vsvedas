import React from 'react';
import {
    Create,
    SimpleForm,
    TextInput,
    DateInput,
    EmailField,
    BooleanInput,
    required,
    minLength,
    maxLength,
    email,
} from 'react-admin';

const ClientCreate = (props) => (
    <Create {...props} title="Crear Cliente">
        <SimpleForm>
            <TextInput
                source="name"
                label="Nombre"
                validate={[required(), minLength(2), maxLength(50)]}
                fullWidth
            />
            <TextInput
                source="lastName"
                label="Apellido"
                validate={[required(), minLength(2), maxLength(50)]}
                fullWidth
            />
            <DateInput
                source="birthDate"
                label="Fecha de Nacimiento"
                validate={required()}
                defaultValue={new Date()}
                options={{ year: 'numeric', month: '2-digit', day: '2-digit' }}
            />
            <TextInput
                source="email"
                label="Correo Electrónico"
                validate={[required(), email()]}
                type="email"
                fullWidth
            />
            <TextInput
                source="cuit"
                label="CUIT"
                validate={[required(), minLength(11), maxLength(11)]}
            />
            <TextInput
                source="numberPhone"
                label="Teléfono"
            />
            <TextInput
                source="address"
                label="Dirección"
                multiline
                fullWidth
            />
        </SimpleForm>
    </Create>
);
export default ClientCreate;
