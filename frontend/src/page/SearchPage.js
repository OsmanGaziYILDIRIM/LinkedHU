import {Stack} from "@mui/material";
import Box from "@mui/material/Box";
import Search from "../component/search/Search";

export default function SearchPage() {
  return (
      <div>
        <Stack direction="row" spacing={2} justifyContent="space-between" marginTop={5}>
          <Box flex={1}></Box>
          <Search></Search>
          <Box flex={1}></Box>
        </Stack>
      </div>
  )
}