import Grid from "@mui/material/Grid";
import {Card, Divider, List, ListItem, ListItemAvatar, ListItemText, Skeleton, Stack, TextField} from "@mui/material";
import Button from "@mui/material/Button";
import {useEffect, useState} from "react";
import InputLabel from "@mui/material/InputLabel";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import * as React from "react";
import {useParams} from "react-router-dom";
import {UserService} from "../../service/UserService";
import SaveIcon from '@mui/icons-material/Save';

const defaultValues = {
    authorities: [],
    name: "",
    surname: "",
    email: "",
    phoneNumber: "",
    graduateYear: "",
    degree: 1,
    department: 1,
    gender: 1,
    text: "",
    usertype: 0
}

export default function ProfileSettings() {
    const userParams = useParams();
    const [user, setUser] = useState([]);


    useEffect(() => {
        UserService.getUserById(userParams.userid).then(response => {
            const userObject = response.data
            setUser(userObject);
        });
    }, []);


    defaultValues.name = user.name
    defaultValues.surname = user.surname
    defaultValues.email = user.username
    defaultValues.phoneNumber = user.phoneNumber
    defaultValues.graduateYear = user.graduate_year
    defaultValues.degree = user.degree
    defaultValues.department = user.department
    defaultValues.gender = user.gender
    defaultValues.text = user.text
    defaultValues.authorities = user.authorities
    defaultValues.usertype = user.usertype
    

    const [formValues, setFormValues] = useState(defaultValues);


    const handleInputChange = (e) => {
        const {name, value} = e.target
        setFormValues({
            ...formValues,
            [name]: value,
        })
        console.log("name: value ->",name, value);
    }


    const handleSubmit = () => {
        const updatedUser = {
            userId: userParams.userid,
            name: formValues.name,
            surname: formValues.surname,
            username: formValues.email,
            phoneNumber: formValues.phoneNumber,
            graduate_year: formValues.graduateYear,
            degree: formValues.degree,
            department: formValues.department,
            gender: formValues.gender,
            text: formValues.text,
            usertype: formValues.usertype
        }
        console.log("handleSubmit", updatedUser);
        UserService.updateUser(updatedUser).then(response => {
            console.log(response);
        }
        )
    }

    const userCredentials = {
        name: user.name,
        surname: user.surname,
        username: user.username,
        phoneNumber: user.phoneNumber,
        graduate_year: user.graduate_year
    }





    const listItemTextBox = (label, defaultValue, setter) => (
        <ListItem>
            <TextField
                fullWidth={true}
                id="outlined-helperText"
                label={label}
                onChange={(event) => setter(event.target.value)}
                variant="standard"
                margin="normal"

            />
        </ListItem>
    )

    if (formValues.usertype === 0 || formValues.usertype === undefined) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Stack direction={'column'}>
                    <Skeleton variant="text" width={360} height={50}/>
                    <Skeleton variant="text" width={360} height={50}/>
                    <Skeleton variant="text" width={360} height={50}/>
                    <Skeleton variant="rect" width={360} height={200}/>
                </Stack>
            </Grid>
        )
    } else if (formValues.usertype === 1) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Card className='profileCard'>
                    <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}} dense={false}>
                        <ListItem>
                            <TextField
                                id="standard-multiline-static"
                                name="text"
                                label="About me"
                                multiline
                                rows="4"
                                defaultValue={defaultValues.text}
                                variant="standard"
                                fullWidth={true}
                                onChange={handleInputChange}
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                name="name"
                                label={'Name'}
                                multiline
                                defaultValue={defaultValues.name}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                name="surname"
                                label={'Surname'}
                                multiline
                                defaultValue={defaultValues.surname}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <FormControl fullWidth={true}
                                         variant="standard"
                                         margin="normal">
                                <InputLabel id="select-department">Department</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    // defaultValue={defaultValues.department}
                                    label="Department"
                                    onChange={handleInputChange}
                                    variant="standard"
                                    name="department"
                                >
                                    <MenuItem value={1}>Computer Engineering</MenuItem>
                                    <MenuItem value={2}>Artificial Intelligence Engineering</MenuItem>
                                </Select>
                            </FormControl>
                        </ListItem>
                        <ListItem>
                            <FormControl fullWidth>
                                <InputLabel id="select-degree">Degree</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    // defaultValue={defaultValues.degree}
                                    label="Degree"
                                    onChange={handleInputChange}
                                    variant="standard"
                                    name="degree"
                                >
                                    <MenuItem value={1}>Bachelor's</MenuItem>
                                    <MenuItem value={2}>Master</MenuItem>
                                    <MenuItem value={3}>PhD</MenuItem>
                                    <MenuItem value={4}>Exchange</MenuItem>
                                </Select>
                            </FormControl>
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Graduation year'}
                                name={'graduateYear'}
                                defaultValue={defaultValues.graduateYear}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Email'}
                                name={'email'}
                                defaultValue={defaultValues.email}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Phone number'}
                                name={'phoneNumber'}
                                defaultValue={defaultValues.phoneNumber}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <FormControl fullWidth>
                                <InputLabel id="select-gender">Gender</InputLabel>
                                <Select
                                    name="gender"
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    // defaultValue={defaultValues.gender}
                                    label="Degree"
                                    onChange={handleInputChange}
                                    variant="standard"

                                >
                                    <MenuItem value={1}>Male</MenuItem>
                                    <MenuItem value={2}>Female</MenuItem>
                                    <MenuItem value={3}>Other</MenuItem>
                                </Select>
                            </FormControl>
                        </ListItem>
                        <Button
                            // href={"/profile/" + userParams.userid }
                            variant="contained"
                            endIcon={<SaveIcon/>}
                            onClick={handleSubmit}
                        >
                            Save
                        </Button>
                        {/*{listItemTextBox("Name", userCredentials.name, setName)}*/}
                        {/*{listItemTextBox("Surname", userCredentials.surname, setSurname)}*/}
                        {/*{listItemTextBox('Graduation Year', userCredentials.graduate_year, setGraduateYear)}*/}
                        {/*{listItemTextBox('Email', userCredentials.username, setEmail)}*/}
                        {/*{listItemTextBox('Phone', userCredentials.phoneNumber, setPhoneNumber)}*/}

                    </List>
                </Card>
            </Grid>
        )
    } else if (formValues.usertype === 2) {
        return (
            <Grid container spacing={1} justifyContent={'center'}>
                <Card className='profileCard'>
                    <List sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}} dense={false}>
                        <ListItem>
                            <TextField
                                id="standard-multiline-static"
                                name="text"
                                label="About me"
                                multiline
                                rows="4"
                                defaultValue={defaultValues.text}
                                variant="standard"
                                fullWidth={true}
                                onChange={handleInputChange}
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                name="name"
                                label={'Name'}
                                multiline
                                defaultValue={defaultValues.name}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                name="surname"
                                label={'Surname'}
                                multiline
                                defaultValue={defaultValues.surname}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Graduation year'}
                                name={'graduateYear'}
                                defaultValue={defaultValues.graduateYear}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Email'}
                                name={'email'}
                                defaultValue={defaultValues.email}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Phone number'}
                                name={'phoneNumber'}
                                defaultValue={defaultValues.phoneNumber}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <FormControl fullWidth>
                                <InputLabel id="select-gender">Gender</InputLabel>
                                <Select
                                    name="gender"
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    // defaultValue={defaultValues.gender}
                                    label="Degree"
                                    onChange={handleInputChange}
                                    variant="standard"
                                >
                                    <MenuItem value={1}>Male</MenuItem>
                                    <MenuItem value={2}>Female</MenuItem>
                                    <MenuItem value={3}>Other</MenuItem>
                                </Select>
                            </FormControl>
                        </ListItem>
                        <Button
                            // href={"/profile/" + userParams.userid }
                            variant="contained"
                            endIcon={<SaveIcon/>}
                            onClick={handleSubmit}
                        >
                            Save
                        </Button>
                    </List>
                </Card>
            </Grid>
        )
    }  else if (formValues.usertype === 3) {
        return (
            <Grid container spacing={1} justifyContent={"center"}>
                <Card className='profileCard'>
                    <List sx={{width: '100%', maxWidth:360, bgcolor: 'background.paper'}} dense={false}>
                        <ListItem>
                            <TextField
                                id="standard-multiline-static"
                                name="text"
                                label="About me"
                                multiline
                                rows="4"
                                defaultValue={defaultValues.text}
                                variant="standard"
                                fullWidth={true}
                                onChange={handleInputChange}
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                name="name"
                                label={'Name'}
                                multiline
                                defaultValue={defaultValues.name}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                name="surname"
                                label={'Surname'}
                                multiline
                                defaultValue={defaultValues.surname}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Email'}
                                name={'email'}
                                defaultValue={defaultValues.email}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <TextField
                                id="standard-helperText"
                                label={'Phone number'}
                                name={'phoneNumber'}
                                defaultValue={defaultValues.phoneNumber}
                                onChange={handleInputChange}
                                fullWidth={true}
                                variant="standard"
                                focused
                                color="secondary"
                                multiline
                            />
                        </ListItem>
                        <ListItem>
                            <FormControl fullWidth>
                                <InputLabel id="select-gender">Gender</InputLabel>
                                <Select
                                    name="gender"
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    // defaultValue={defaultValues.gender}
                                    label="Degree"
                                    onChange={handleInputChange}
                                    variant="standard"

                                >
                                    <MenuItem value={1}>Male</MenuItem>
                                    <MenuItem value={2}>Female</MenuItem>
                                    <MenuItem value={3}>Other</MenuItem>
                                </Select>
                            </FormControl>
                        </ListItem>
                        <Button
                            // href={"/profile/" + userParams.userid }
                            variant="contained"
                            endIcon={<SaveIcon/>}
                            onClick={handleSubmit}
                        >
                            Save
                        </Button>
                    </List>
                </Card>
            </Grid>
        )
    }
}