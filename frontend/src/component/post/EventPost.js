import JobPic from '../../asset/image/event.jpeg';
import {useTheme} from '@mui/material/styles';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import "./Post.css";
import LocationOnIcon from '@mui/icons-material/LocationOn';
import EventIcon from '@mui/icons-material/Event';
import Button from '@mui/material/Button';
import Grid from "@mui/material/Grid";

export default function EventPost({eventName, eventDate, eventLocation, eventDescription, eventLink}) {

    return (
        <Card className={"jobCard"} sx={{display: 'flex'/*, alignItems:"center"*/}}>
            <CardMedia className={"mediaPhoto"}
                       component="img"
                       sx={{width: 211}}
                       image={JobPic}
            />
            <Box sx={{display: 'flex', flexDirection: 'column', with:"100%"}}>
                <CardContent sx={{flex: '1 0 auto'}}>

                    <Box sx={{display: 'flex', alignItems: 'center', pl: 1, pb: 1}}>

                        <Box sx={{display: 'flex', alignItems: 'center', width: '50%', paddingLeft: 1}}>
                            <Typography component="div" variant="h5">
                                {eventName}
                            </Typography>
                        </Box>
                        <Box className={"applyNowButton"} sx={{display: 'flex', alignItems: 'center', width: '50%'}}>
                            <Typography component="div" variant="h7">
                                {eventLocation}
                            </Typography>
                            <LocationOnIcon/>
                        </Box>

                    </Box>

                    <Typography variant="subtitle1" color="text.secondary" component="div">
                        {eventDescription}
                    </Typography>
                </CardContent>
                <Box sx={{display: 'flex', alignItems: 'center', pl: 1, pb: 1}}>

                    <Box sx={{display: 'flex', alignItems: 'center', width: '50%', paddingLeft: 1}}>
                        <EventIcon/>
                        <Typography variant="subtitle1" color="black" component="div">
                            {eventDate}
                        </Typography>
                    </Box>
                    <Box className={"applyNowButton"} sx={{display: 'flex', alignItems: 'center', width: '50%', justifyContent:"right"}}>
                        <Button variant="contained" color="primary" target="_blank" href={eventLink} sx={{marginRight:0}}>
                            Go to Event
                        </Button>



                    </Box>


                </Box>
            </Box>

        </Card>
    );
}
