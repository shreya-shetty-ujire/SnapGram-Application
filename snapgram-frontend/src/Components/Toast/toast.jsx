"use client";

import { Button, useToast } from "@chakra-ui/react";

const ToastDemo = () => {
  const toast = useToast();

  return (
    <Button
      variant="outline"
      size="sm"
      onClick={() =>
        toast({
          title: "Success",
          description: "File saved successfully",
          status: "success",
          duration: 3000,
          isClosable: true,
        })
      }
    >
      Show Success Toast
    </Button>
  );
};

export default ToastDemo;
