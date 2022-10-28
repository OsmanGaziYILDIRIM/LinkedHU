import React from "react";
import "./HomeLayout.css"
import { makeStyles } from "@mui/styles";
import Grid from "@mui/material/Grid";
import Sidebar from "../profileCard/SideBar";
import Widget from "../widget/Widget";
import {Hidden} from "@mui/material";
import SharePost from "../sharePost/SharePost";
import Feed from "../feed/Feed";
import EventPost from "../post/EventPost";

const useStyles = makeStyles({
    container: {
        height: "100%", // So that grids 1 & 4 go all the way down
        minHeight: 150, // Give minimum height to a div
        border: "1px solid black",
        fontSize: 30,
        textAlign: "center"

    },
    containerTall: {
        minHeight: 250 // This div has higher minimum height
    }
});

export default function HomeLayout() {

    return (

        <Grid container spacing={3} justifyContent={"center"} >
            <Hidden smDown>
                <Grid item sx={{ display: { xs: 'none', sm: 'block' } }} md={2} lg={2} >
                    <Sidebar />
                </Grid>
            </Hidden>

            <Grid item xs={11} sm={11} md={5} lg={5}>
                {localStorage.getItem("authority") ==="1" ?
                    <br/>
                    :
                    <div className="messageShare">
                        <SharePost/>
                    </div>

                }
                <br/>
                <Feed/>

            </Grid>


            {/*<Hidden smDown>*/}

            {/*    <Grid item xs={8} sm={3} md={3} lg={3}>*/}

            {/*        <Widget />*/}
            {/*    </Grid>*/}
            {/*</Hidden>*/}

        </Grid>

    );
}