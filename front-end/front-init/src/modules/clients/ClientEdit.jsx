import React from 'react';
import {
    Edit,
    SimpleForm,
    TextInput,
    DateInput,
    BooleanInput,
    NumberInput,
    required,
    minLength,
    maxLength,
    email,
    Toolbar,
    SaveButton,
    DeleteButton,
} from 'react-admin';

const ClientEditToolbar = props => (
    <Toolbar {...props}>
        <SaveButton label="Guardar" />
    </Toolbar>
);

const ClientEdit = (props) => (
    <Edit {...props} title="Editar Cliente">
        <SimpleForm toolbar={<ClientEditToolbar />}>
            {/* ID (solo lectura) */}
            <NumberInput 
                source="id" 
                label="ID"
                disabled
                fullWidth
            />

            {/* Campos principales */}
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
                options={{ year: 'numeric', month: '2-digit', day: '2-digit' }}
            />
            
            {/* Email con validación */}
            <TextInput 
                source="email" 
                label="Correo Electrónico"
                validate={[required(), email()]}
                type="email"
                fullWidth
            />
            
            {/* CUIT con formato */}
            <TextInput 
                source="cuit" 
                label="CUIT"
                validate={[required(), minLength(11), maxLength(11)]}
                format={value => value?.replace(/(\d{2})(\d{8})(\d{1})/, '$1-$2-$3') || ''}
                parse={value => value?.replace(/\D/g, '')}
            />
            
            {/* Campos opcionales */}
            <TextInput 
                source="numberPhone" 
                label="Teléfono"
                format={value => value?.replace(/(\d{2})(\d{4})(\d{4})/, '$1 $2-$3') || ''}
                parse={value => value?.replace(/\D/g, '')}
            />
            
            <TextInput 
                source="address" 
                label="Dirección"
                multiline
                fullWidth
            />
            
            
        </SimpleForm>
    </Edit>
);

export default ClientEdit;