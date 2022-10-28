import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {useState} from "react";
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import EventIcon from "@mui/icons-material/Event";
import {PostService} from "../../service/PostService";
import MenuItem from "@mui/material/MenuItem";
import WorkIcon from "@mui/icons-material/Work";


const defaultValues = {
    eventTitle: "",
    location: "",
    date: new Date(),
    link: "",
    description: "",
};

export default function EventPostForm({updateMode=false,postId, postType, location,
                                          liveEventLink, liveEventDescription, eventTitle, eventDate}) {
    if (updateMode) {
        defaultValues.location = location;
        defaultValues.link = liveEventLink;
        defaultValues.description = liveEventDescription;
        defaultValues.eventTitle = eventTitle;
        defaultValues.date = eventDate;
    }
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
        let eventDateStr = formValues.date;
        let dd = String(eventDateStr.getDate()).padStart(2, '0');
        let mm = String(eventDateStr.getMonth() + 1).padStart(2, '0');
        let yyyy = eventDateStr.getFullYear();

        eventDateStr = mm + '/' + dd + '/' + yyyy;
        PostService.createEventPost(localStorage.getItem("userId"), formValues.link, formValues.description, formValues.eventTitle, eventDateStr, formValues.location).then(res => {
            setOpen(false);
            console.log("Event post created successfully");
            window.location.reload();
        })
        setOpen(false);
    }
    const handleUpdate = (event) => {
        let eventDateStr = formValues.date;
        let dd = String(eventDateStr.getDate()).padStart(2, '0');
        let mm = String(eventDateStr.getMonth() + 1).padStart(2, '0');
        let yyyy = eventDateStr.getFullYear();

        eventDateStr = mm + '/' + dd + '/' + yyyy;
        PostService.updateEventPost(postId, postType,localStorage.getItem("userId"),
            formValues.location, formValues.link, formValues.description,formValues.eventTitle,
            eventDateStr).then(() => {
                window.location.reload();
            }
        )
        setOpen(false);
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                {updateMode ?
                    <MenuItem onClick={handleClickOpen}>Update</MenuItem>
                    :
                    <div className="messageSender__option" onClick={handleClickOpen}>
                        <EventIcon style={{ color: "red" }} />
                        <h3>Event</h3>
                    </div>
                }

                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Event Post</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Please fill in the form below to post a event.
                        </DialogContentText>
                        <TextField
                            required
                            margin="dense"
                            id="eventTitle"
                            name="eventTitle"
                            label="Event Title"
                            type="text"
                            fullWidth
                            variant="standard"
                            defaultValue={formValues.eventTitle}
                            onChange={handleInputChange}
                        />
                        <TextField
                            required
                            margin="dense"
                            id="location"
                            name="location"
                            label="Location"
                            type="text"
                            fullWidth
                            variant="standard"
                            defaultValue={formValues.location}
                            onChange={handleInputChange}
                        />
                        <br/>
                        <br/>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                        <DesktopDatePicker
                            id="date"
                            name="date"
                            label="Date desktop"
                            inputFormat="dd/MM/yyyy"
                            value={formValues.date}
                            onChange={(date) => setFormValues({ ...formValues, date })}
                            renderInput={(params) => <TextField {...params} />}
                        />
                        </LocalizationProvider>
                        <TextField
                            margin="dense"
                            id="link"
                            name="link"
                            label="Event Link"
                            type="text"
                            fullWidth
                            variant="standard"
                            defaultValue={formValues.link}
                            onChange={handleInputChange}
                        />
                        <TextField
                            required
                            margin="dense"
                            id="description"
                            name="description"
                            label="Event Description"
                            type="description"
                            fullWidth
                            variant="standard"
                            multiline
                            rows={4}
                            defaultValue={formValues.description}
                            onChange={handleInputChange}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose}>Cancel</Button>
                        {updateMode ?
                            <Button onClick={handleUpdate}>Update</Button>
                            :
                            <Button onClick={handleSubmit}>Share</Button>
                        }

                    </DialogActions>
                </Dialog>
            </div>
        </form>
    );
}
