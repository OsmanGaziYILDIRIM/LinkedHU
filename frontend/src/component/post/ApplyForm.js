import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {useEffect, useState} from "react";
import {UserService} from "../../service/UserService";

const defaultValues = {
    fullName: "",
    email: "",
    phone: "",
    gpa: 0,
    coverLetter: "",
};

export default function ApplyForm({fullName, email}) {
    defaultValues.fullName = fullName;
    defaultValues.email = email;



    const [formValues, setFormValues] = useState(defaultValues);


    const handleInputChange = (e) => {

        const { name, value } = e.target;
        setFormValues({
            ...formValues,
            [name]: value,
        });
    };
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {

        setOpen(false);
    };
    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(formValues);
        setOpen(false);
    }

    return (
        <form onSubmit={handleSubmit}>
        <div>
            <Button sx={{marginRight:5}} onClick={handleClickOpen}  variant="contained">Apply Now</Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>Job Application</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Please fill in the form below to apply for the job.
                    </DialogContentText>
                    <TextField
                        margin="dense"
                        id="fullName"
                        name="fullName"
                        label="Full Name"
                        type="text"
                        fullWidth
                        variant="standard"
                        InputProps={{
                            readOnly: true,
                        }}
                        defaultValue={formValues.fullName}
                        onChange={handleInputChange}
                    />
                    <TextField
                        margin="dense"
                        id="email"
                        name="email"
                        label="Email"
                        type="email"
                        fullWidth
                        variant="standard"
                        defaultValue={formValues.email}
                        onChange={handleInputChange}

                    />
                    <TextField
                        margin="dense"
                        id="phone"
                        name="phone"
                        label="Phone"
                        type="tel"
                        fullWidth
                        variant="standard"
                        defaultValue={formValues.phone}
                        onChange={handleInputChange}
                    />
                    <TextField
                        margin="dense"
                        id="gpa"
                        name="gpa"
                        label="GPA"
                        type="number"
                        inputProps={{"min": "0", "max": "4", "step": "0.01"}}
                        fullWidth
                        variant="standard"
                        defaultValue={formValues.gpa}
                        onChange={handleInputChange}
                    />
                    <TextField
                        focused
                        margin="dense"
                        id="resume"
                        name="resume"
                        label="Resume"
                        type="file"
                        fullWidth
                        variant="standard"
                    />
                    <TextField
                        margin="dense"
                        id="coverLetter"
                        name="coverLetter"
                        label="Cover Letter"
                        type="text"
                        fullWidth
                        variant="standard"
                        multiline
                        rows={4}
                        defaultValue={formValues.coverLetter}
                        onChange={handleInputChange}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleSubmit}>Apply</Button>
                </DialogActions>
            </Dialog>
        </div>
        </form>
    );
}
