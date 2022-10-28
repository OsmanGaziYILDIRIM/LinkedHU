import React from 'react';
import {Item, Row} from "@mui-treasury/components/flex";
import Avatar from "@mui/material/Avatar";
import {Info, InfoSubtitle, InfoTitle} from "@mui-treasury/components/info";
import {useMusicInfoStyles} from "@mui-treasury/styles/info/music";
import {useGrowAvatarStyles} from "@mui-treasury/styles/avatar/grow";



export default function ChatListItem({fullName, lastMessage, userId, date}) {
    const commonProps = {
        blur: '12px',
        radius: 16,
        size: 48,
        opacity: 0.6,
    };
    const src =
        'https://www.personality-insights.com/wp-content/uploads/2017/12/default-profile-pic-e1513291410505.jpg';
    const avatarStyles = useGrowAvatarStyles({ src, ...commonProps });

    const handleItemClick = () => {
        console.log('item clicked');
    };

  return (
      <Row onClick={handleItemClick} sx={{marginBottom:2, cursor:"pointer"} }>
          <Item>
              <div className={avatarStyles.root}>
                  <Avatar src={src} />
              </div>
          </Item>
          <Info useStyles={useMusicInfoStyles} minWidth={0}>
              <InfoTitle>{fullName}</InfoTitle>
              <InfoSubtitle>{lastMessage}</InfoSubtitle>
          </Info>
          {/*<Item position={'right'}>*/}
          {/*    <IconButton size={'small'}>*/}
          {/*        <MoreHoriz />*/}
          {/*    </IconButton>*/}
          {/*</Item>*/}
      </Row>
  );
}